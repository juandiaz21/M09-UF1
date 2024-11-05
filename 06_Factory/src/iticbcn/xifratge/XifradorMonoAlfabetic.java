package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorMonoAlfabetic implements Xifrador {

    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static char[] alfabetPermutat = permutaAlfabet(lletres);

    public static char[] permutaAlfabet(char[] lletresEntrada) {
        char[] lletresPermutades = lletresEntrada.clone();
        Random rand = new Random();
        List<Character> cadenaLletres = new ArrayList<>();
        for (char c : lletresEntrada) {
            cadenaLletres.add(c);
        }
        Collections.shuffle(cadenaLletres, rand);

        for (int i = 0; i < lletres.length; i++) {
            lletresPermutades[i] = cadenaLletres.get(i);
        }
        return lletresPermutades;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null.");
        }
        String xifrada = xifraMonoAlfa(msg);
        return new TextXifrat(xifrada.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null.");
        }
        String msgXifrat = new String(xifrat.getBytes());
        return desxifraMonoAlfa(msgXifrat);
    }
    public String xifraMonoAlfa(String cadena) {
        String xifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0; i < cadena.length(); i++) {
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
    public String desxifraMonoAlfa(String cadena) {
        String desxifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0; i < cadena.length(); i++) {
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
