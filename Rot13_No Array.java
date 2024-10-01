import java.util.Scanner;
public class Rot13 {
    
    public static final char[] lletres = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] lletresMaj = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String xifraRot13 (String cadena){
        String frase = "";
        String cadenaMin = cadena.toLowerCase();
        for (int x = 0 ; x < cadenaMin.length() ; x++){
            if (Character.isLetter(cadenaMin.charAt(x))){
                for (int i = 0 ; i < lletres.length ; i++){
                    if (cadenaMin.charAt(x) ==  lletres[i]){
                        if (i+13 < lletres.length){
                            if (Character.isLowerCase(cadena.charAt(x))){
                                frase= frase + lletres[i+13];
                            }else{
                                frase= frase + lletresMaj[i+13];
                            }
                        }else{
                            if (Character.isLowerCase(cadena.charAt(x))){
                                frase = frase + (lletres[i+13- lletres.length]);
                            }else{
                                frase = frase + (lletresMaj[i+13 - lletresMaj.length] );
                            }
                        }
                    }
                }
            }else{
                frase = frase + cadena.charAt(x);
            }
        }
        return frase;
    }    

    public static String desxifraRot13 (String cadena){
        String frase = "";
        String cadenaMin = cadena.toLowerCase();
        for (int x = 0 ; x < cadenaMin.length() ; x++){
            if (Character.isLetter(cadenaMin.charAt(x))){
                for (int i = 0 ; i < lletres.length-1 ; i++){
                    if (cadenaMin.charAt(x) ==  lletres[i]){
                        if (i-13 >= 0){
                            if (Character.isLowerCase(cadena.charAt(x))){
                                frase= frase + lletres[i-13];
                            }else{
                                frase= frase + lletresMaj[i-13];
                            }
                        }else{
                            if (Character.isLowerCase(cadena.charAt(x))){
                                frase= frase + (lletres[i-13 + lletres.length] );
                            }else{
                                frase= frase + (lletresMaj[i-13 + lletresMaj.length] );
                            }
                        }
                    }
                }
            }else{
                frase = frase + cadena.charAt(x);
            }
        }
        return frase;
    }


    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        System.out.print("Dime una palabra: ");
        String paraula = sc.nextLine();

        System.out.println("Vols XIFRAR(X) o DESXIFRAR(D)?");
        String resposta = sc.nextLine();
        if (!Comprova(resposta)){
            while (!Comprova(resposta)){
                System.out.println("Torno a preguntar, vols XIFRAR(X) o DESXIFRAR(D)?");
                resposta = sc.nextLine(); 
            }
            if (resposta.equals("X")||resposta.equals("x")){
                System.out.println("La paraula que m'has demanat xifrada per Rot13 queda: " + xifraRot13(paraula));
            }else if (resposta.equals("D")||resposta.equals("d")){
                System.out.println("La paraula que m'has demanat desxifrada per Rot13 queda: " + desxifraRot13(paraula)); 
            }
        }else{
            if (resposta.equals("X")||resposta.equals("x")){
                System.out.println("La paraula que m'has demanat xifrada per Rot13 queda: " + xifraRot13(paraula));
            }else if (resposta.equals("D")||resposta.equals("d")){
                System.out.println("La paraula que m'has demanat desxifrada per Rot13 queda: " + desxifraRot13(paraula)); 
            }

        }
        sc.close();
    }
    
    public static boolean Comprova(String resposta){
        if (!resposta.equals("X") && !resposta.equals("x") && !resposta.equals("D") && !resposta.equals("d")){
            return false;
        }else{
            return true;
        }
    }
}  
