package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPoliAlfabetic implements Xifrador {

    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static char[] alfabetPermutat;
    public static Random rand;

    public void initRandom(long clau) {
        rand = new Random(clau);
    }
    public void permutaAlfabet() {
        char[] lletresPermutades = lletres.clone();
        List<Character> cadenaLletres = new ArrayList<>();
        for (char c : lletres) {
            cadenaLletres.add(c);
        }
        Collections.shuffle(cadenaLletres, rand);

        for (int i = 0; i < lletres.length; i++) {
            lletresPermutades[i] = cadenaLletres.get(i);
        }
        alfabetPermutat = lletresPermutades;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long clauLong = Long.parseLong(clau);  
            initRandom(clauLong);  
            String xifrada = xifraPoliAlfa(msg);
            return new TextXifrat(xifrada.getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long.");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            long clauLong = Long.parseLong(clau); 
            initRandom(clauLong); 
            String msgXifrat = new String(xifrat.getBytes()); 
            return desxifraPoliAlfa(msgXifrat);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long.");
        }
    }
    public String xifraPoliAlfa(String cadena) {
        String xifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet();  
            if (Character.isLetter(cadena.charAt(i))) {
                for (int n = 0; n < lletres.length; n++) {
                    if (cadenaMin.charAt(i) == lletres[n]) {
                        if (Character.isLowerCase(cadena.charAt(i))) {
                            xifrada = xifrada + alfabetPermutat[n];
                        } else {
                            xifrada = xifrada + Character.toUpperCase(alfabetPermutat[n]);
                        }
                    }
                }
            } else {
                xifrada = xifrada + cadena.charAt(i);
            }
        }
        return xifrada;
    }
    public String desxifraPoliAlfa(String cadena) {
        String desxifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet(); 
            if (Character.isLetter(cadena.charAt(i))) {
                for (int n = 0; n < alfabetPermutat.length; n++) {
                    if (cadenaMin.charAt(i) == alfabetPermutat[n]) {
                        if (Character.isLowerCase(cadena.charAt(i))) {
                            desxifrada = desxifrada + lletres[n];
                        } else {
                            desxifrada = desxifrada + Character.toUpperCase(lletres[n]);
                        }
                    }
                }
            } else {
                desxifrada = desxifrada + cadena.charAt(i);
            }
        }
        return desxifrada;
    }

}
