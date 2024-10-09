/*Programa de java que xifra mitjançant la tècnica polialfabetica*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PoliAlfabetic {
    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static char[] alfabetPermutat; 
    public static int clauSecreta = 12;
    public static Random rand;
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año, ?¿¡..;;", "Test 03 Peça, Òrrius, Bòvila, 1234567"};
    
        String msgsXifrats[] = new String[msgs.length];
    
        System.out.println("Xifratge:\n");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
    
        System.out.println("Desxifratge:\n");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(int clau){
        rand = new Random(clau);
    }
    
    public static void permutaAlfabet(char[] lletresEntrada) {
        char[] lletresPermutades = lletresEntrada.clone(); 
        List<Character> cadenaLletres = new ArrayList<>();
        for (char c : lletresEntrada){
            cadenaLletres.add(c);
        }
        Collections.shuffle(cadenaLletres,rand);
        
        for(int i = 0 ; i < lletres.length ; i++){
            lletresPermutades[i] = cadenaLletres.get(i); 
        }
        alfabetPermutat = lletresPermutades;
    }

    public static String xifraPoliAlfa(String cadena){
        String xifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0 ; i < cadena.length() ; i++){
            permutaAlfabet(lletres);
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
    public static String desxifraPoliAlfa(String cadena){
        String desxifrada = "";
        String cadenaMin = cadena.toLowerCase();     
        for (int i = 0 ; i < cadena.length() ; i++){
            permutaAlfabet(lletres);
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
    public static boolean comprovaResposta(String resposta){
        if(resposta.equals("C") || resposta.equals("c")||resposta.equals("d")||resposta.equals("D")){
            return true;
        }
        return false;
    }
    public static boolean esXifra(String resposta ){
        if (resposta.equals("c") || resposta.equals("C")){
            return true;
        }
        return false;
    }

}
