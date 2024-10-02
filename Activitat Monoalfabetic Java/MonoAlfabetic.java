import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class MonoAlfabetic {
    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] alfabetPermutat = permutaAlfabet(lletres);
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in, "UTF-8");
        System.out.print("Dime una palabra: ");
        String palabra = sc.nextLine();
        System.out.println(alfabetPermutat);
        System.out.print("Que quieres hacer: CIFRARLA (C) O DESCIFRARLA (D)? ");
        String resposta = sc.nextLine();
        if (!comprovaResposta(resposta)){
            while (!comprovaResposta(resposta)){
                System.out.print("Te vuelvo a preguntar. Que quieres hacer: CIFRARLA (C) O DESCIFRARLA (D)? ");
                resposta = sc.nextLine();
            }
        }
        if (esXifra(resposta)){
            System.out.println("La entrada que me has pedido cifrar queda: " + xifraMonoAlfa(palabra));
        }else{
            System.out.println("La entrada que me has pedido descifrar queda: " + desxifraMonoAlfa(palabra));
        }
        sc.close();
    }
    public static char[] permutaAlfabet(char[] lletresEntrada) {
        char[] lletresPermutades = lletresEntrada.clone(); 
        Random rand = new Random();
        List<Character> cadenaLletres = new ArrayList<>();
        for (char c : lletresEntrada){
            cadenaLletres.add(c);
        }
        Collections.shuffle(cadenaLletres,rand);
        
        for(int i = 0 ; i < lletres.length ; i++){
            lletresPermutades[i] = cadenaLletres.get(i); 
        }
        return lletresPermutades;
    }
    public static String xifraMonoAlfa(String cadena){
        String xifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0 ; i < cadena.length() ; i++){
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
    public static String desxifraMonoAlfa(String cadena){
        String desxifrada = "";
        String cadenaMin = cadena.toLowerCase();
        for (int i = 0 ; i < cadena.length() ; i++){
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