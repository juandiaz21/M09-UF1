package iticbcn.xifratge; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPoliAlfabetic {
    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static char[] alfabetPermutat; 
    public static int clauSecreta = 12;
    public static Random rand;

    public void initRandom(int clau){
        rand = new Random(clau);
    }
    
    public void permutaAlfabet() {
        char[] lletresPermutades = lletres.clone(); 
        List<Character> cadenaLletres = new ArrayList<>();
        for (char c : lletres){
            cadenaLletres.add(c);
        }
        Collections.shuffle(cadenaLletres,rand);
        
        for(int i = 0 ; i < lletres.length ; i++){
            lletresPermutades[i] = cadenaLletres.get(i); 
        }
        alfabetPermutat = lletresPermutades;
    }

    public String xifraPoliAlfa(String cadena){
        String xifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0 ; i < cadena.length() ; i++){
            permutaAlfabet();
            if (Character.isLetter(cadena.charAt(i))){
                for (int n = 0 ; n < lletres.length ; n++){
                    if (cadenaMin.charAt(i) ==  lletres[n]){
                        if (Character.isLowerCase(cadena.charAt(i))){
                            xifrada= xifrada + alfabetPermutat[n];
                        }else{
                            xifrada= xifrada + Character.toUpperCase(alfabetPermutat[n]);
                        }
                    }
                }
            }else{
                xifrada = xifrada + cadena.charAt(i);
            }
        }
        return xifrada;
    }
    public String desxifraPoliAlfa(String cadena){
        String desxifrada = "";
        String cadenaMin = cadena.toLowerCase();     
        for (int i = 0 ; i < cadena.length() ; i++){
            permutaAlfabet();
            if (Character.isLetter(cadena.charAt(i))){
                for (int n = 0 ; n < alfabetPermutat.length ; n++){
                    if (cadenaMin.charAt(i) ==  alfabetPermutat[n]){
                        if (Character.isLowerCase(cadena.charAt(i))){
                            desxifrada= desxifrada + lletres[n];
                        }else{
                            desxifrada= desxifrada + Character.toUpperCase(lletres[n]);
                        }
                    }
                }
            }else{
                desxifrada = desxifrada + cadena.charAt(i);
            }
        }
        return desxifrada;
    }
    public boolean comprovaResposta(String resposta){
        if(resposta.equals("C") || resposta.equals("c")||resposta.equals("d")||resposta.equals("D")){
            return true;
        }
        return false;
    }
    public boolean esXifra(String resposta ){
        if (resposta.equals("c") || resposta.equals("C")){
            return true;
        }
        return false;
    }

}
