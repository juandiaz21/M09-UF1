package iticbcn.xifratge; 
import java.util.Scanner;

public class XifradorRotX {
    
    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] lletresMaj = {'A','À','Á', 'B', 'C', 'Ç', 'D', 'E','È','É', 'F', 'G', 'H', 'I','Í','Ï', 'J', 'K', 'L', 'M', 'N','Ñ', 'O','Ó','Ò', 'P', 'Q', 'R', 'S', 'T', 'U','Ú','Ü', 'V', 'W', 'X', 'Y', 'Z'};


    public String xifraRotX( String cadenaEntrada, int desplaçament ){
        String xifrada = "";
        String cadena = corregirPalabra(cadenaEntrada).toLowerCase();
        for (int x = 0 ; x < cadena.length() ; x++){
            if (Character.isLetter(cadena.charAt(x))){
                for (int i = 0 ; i < lletres.length ; i++){
                    if (cadena.charAt(x) ==  lletres[i]){
                        if (i+desplaçament < lletres.length){
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))){
                                xifrada= xifrada + lletres[i+desplaçament];
                            }else{
                                xifrada= xifrada + lletresMaj[i+desplaçament];
                            }
                        }else{
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))){
                                xifrada = xifrada + (lletres[i+desplaçament- lletres.length]);
                            }else{
                                xifrada = xifrada + (lletresMaj[i+desplaçament - lletresMaj.length] );
                            }
                        }
                    }
                }
            }else{
                xifrada = xifrada + cadena.charAt(x);
            }
        }
        return xifrada;
    }


    public String desxifraRotX (String cadenaEntrada, int desplaçament ){
        String desxifrada = "";
        String cadena = corregirPalabra(cadenaEntrada).toLowerCase();
        for (int x = 0 ; x < cadena.length() ; x++){
            if (Character.isLetter(cadena.charAt(x))){
                for (int i = 0 ; i < lletres.length-1 ; i++){
                    if (cadena.charAt(x) ==  lletres[i]){
                        if (i-desplaçament >= 0){
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))){
                                desxifrada= desxifrada + lletres[i-desplaçament];
                            }else{
                                desxifrada= desxifrada + lletresMaj[i-desplaçament];
                            }
                        }else{
                            if (Character.isLowerCase(cadenaEntrada.charAt(x))){
                                desxifrada= desxifrada + (lletres[i-desplaçament + lletres.length] );
                            }else{
                                desxifrada= desxifrada + (lletresMaj[i-desplaçament + lletresMaj.length] );
                            }
                        }
                    }
                }
            }else{
                desxifrada = desxifrada + cadena.charAt(x);
            }
        }
        return desxifrada;
    }

    public String[] forcaBrutaRotX(String cadena){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digues el nombre pel desplaçament: ");
        int nombre = Integer.parseInt(sc.nextLine());
        if (!comprovaNombre(nombre)){
            while (!comprovaNombre(nombre)){
                System.out.print("El nombre ha de ser menor a " + lletres.length + ". Digues un altre nombre pel desplaçament: ");
                nombre = Integer.parseInt(sc.nextLine());
            }
        }
        cadena = xifraRotX(cadena,nombre);
        String[] desxifrades;
        desxifrades = new String[lletres.length];
        String frase = "";
        String cadenaMin = cadena.toLowerCase();
        for (int k = 1 ; k < lletres.length ; k++){ 
            for (int x = 0 ; x < cadenaMin.length() ; x++){
                if (Character.isLetter(cadenaMin.charAt(x))){
                    for (int i = 0 ; i < lletres.length-1 ; i++){
                        if (cadenaMin.charAt(x) ==  lletres[i]){
                            if (i-k >= 0){
                                if (Character.isLowerCase(cadena.charAt(x))){
                                    frase= frase + lletres[i-k];
                                }else{
                                    frase= frase + lletresMaj[i-k];
                                }
                            }else{
                                if (Character.isLowerCase(cadena.charAt(x))){
                                    frase= frase + (lletres[i-k + lletres.length] );
                                }else{
                                    frase= frase + (lletresMaj[i-k + lletresMaj.length] );
                                }
                            }
                        }
                    }
                }else{
                    frase = frase + cadena.charAt(x);
                }
            }
            desxifrades[k] = frase;
            frase = "";
        }
        sc.close();
        return desxifrades;
    }
    
    public boolean comprova(String resposta){
        if (!resposta.equals("X") && !resposta.equals("x") && !resposta.equals("D") && !resposta.equals("d") && !resposta.equals("F") && !resposta.equals("f")){
            return false;
        }else{
            return true;
        }
    }

    public String respostaRot(String paraula,String resposta){
        Scanner sc = new Scanner(System.in);
        String frase= "";
        System.out.print("Digues el nombre pel desplaçament: ");
        int nombre = Integer.parseInt(sc.nextLine());
        
        if (!comprovaNombre(nombre)){
            while (!comprovaNombre(nombre)){
                System.out.print("El nombre ha de ser menor a " + lletres.length + ". Digues un altre nombre pel desplaçament: ");
                nombre = Integer.parseInt(sc.nextLine());
            }
        }

        if (resposta.equals("X")||resposta.equals("x")){
            frase = ("La paraula que m'has demanat xifrada per Rot" + nombre +" queda: " + xifraRotX(corregirPalabra(paraula),nombre));
        }else if (resposta.equals("D")||resposta.equals("d")){
            frase = ("La paraula que m'has demanat desxifrada per Rot"+nombre +" queda: " + desxifraRotX(corregirPalabra(paraula),nombre)); 
        }

        sc.close();
        return frase;
    }


    public boolean comprovaNombre(int nombre){
        if (nombre>lletres.length){
            return false;
        }else{
            return true;
        }
    }
}
