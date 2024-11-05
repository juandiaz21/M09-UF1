package iticbcn.xifratge; 

public class XifradorRotX implements Xifrador {

    public static final char[] lletres = {'a','à','á','ä', 'b', 'c', 'ç', 'd', 'e','è','é','ë', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò','ö', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] lletresMaj = {'A','À','Á','Ä', 'B', 'C', 'Ç', 'D', 'E','È','É','Ë', 'F', 'G', 'H', 'I','Í','Ï', 'J', 'K', 'L', 'M', 'N','Ñ', 'O','Ó','Ò','Ö', 'P', 'Q', 'R', 'S', 'T', 'U','Ú','Ü', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau); 
            if (desplaçament < 0 || desplaçament >= lletres.length) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + (lletres.length - 1));
            }

            String xifrada = xifraRotX(msg, desplaçament);
            return new TextXifrat(xifrada.getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + (lletres.length - 1));
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau); 
            if (desplaçament < 0 || desplaçament >= lletres.length) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + (lletres.length - 1));
            }

            String msgXifrat = new String(xifrat.getBytes()); 
            return desxifraRotX(msgXifrat, desplaçament);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + (lletres.length - 1));
        }
    }

    public String xifraRotX(String cadenaEntrada, int desplaçament) {
        String xifrada = "";
        String cadena = cadenaEntrada.toLowerCase();
        for (int x = 0; x < cadena.length(); x++) {
            if (Character.isLetter(cadena.charAt(x))) {
                for (int i = 0; i < lletres.length; i++) {
                    if (cadena.charAt(x) == lletres[i]) {
                        if (i + desplaçament < lletres.length) {
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))) {
                                xifrada = xifrada + lletres[i + desplaçament];
                            } else {
                                xifrada = xifrada + lletresMaj[i + desplaçament];
                            }
                        } else {
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))) {
                                xifrada = xifrada + lletres[i + desplaçament - lletres.length];
                            } else {
                                xifrada = xifrada + lletresMaj[i + desplaçament - lletresMaj.length];
                            }
                        }
                    }
                }
            } else {
                xifrada = xifrada + cadena.charAt(x);
            }
        }
        return xifrada;
    }

    public String desxifraRotX(String cadenaEntrada, int desplaçament) {
        String desxifrada = "";
        String cadena = cadenaEntrada.toLowerCase();
        for (int x = 0; x < cadena.length(); x++) {
            if (Character.isLetter(cadena.charAt(x))) {
                for (int i = 0; i < lletres.length; i++) {
                    if (cadena.charAt(x) == lletres[i]) {
                        if (i - desplaçament >= 0) {
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))) {
                                desxifrada = desxifrada + lletres[i - desplaçament];
                            } else {
                                desxifrada = desxifrada + lletresMaj[i - desplaçament];
                            }
                        } else {
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))) {
                                desxifrada = desxifrada + (lletres[i - desplaçament + lletres.length]);
                            } else {
                                desxifrada = desxifrada + (lletresMaj[i - desplaçament + lletresMaj.length]);
                            }
                        }
                    }
                }
            } else {
                desxifrada = desxifrada + cadena.charAt(x);
            }
        }
        return desxifrada;
    }
}
