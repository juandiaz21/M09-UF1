import java.util.Scanner;

public class RotX {
    
    public static final char[] lletres = {'a','à','á', 'b', 'c', 'ç', 'd', 'e','è','é', 'f', 'g', 'h', 'i','í','ï', 'j', 'k', 'l', 'm', 'n','ñ', 'o','ó','ò', 'p', 'q', 'r', 's', 't', 'u','ú','ü', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] lletresMaj = {'A','À','Á', 'B', 'C', 'Ç', 'D', 'E','È','É', 'F', 'G', 'H', 'I','Í','Ï', 'J', 'K', 'L', 'M', 'N','Ñ', 'O','Ó','Ò', 'P', 'Q', 'R', 'S', 'T', 'U','Ú','Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in, "UTF-8");
        String[] cuenta;
        System.out.print("Digues una entrada de text per xifrar/desxifrar: ");
        String paraula = sc.nextLine();
        System.out.println("Vols XIFRAR(X) , DESXIFRAR(D) o FORÇA BRUTA(F)?");
        String resposta = sc.nextLine();
        if (!comprova(resposta)){
            while (!comprova(resposta)){
                System.out.println("Torno a preguntar, vols XIFRAR(X) , DESXIFRAR(D) o FORÇA BRUTA(F)?");
                resposta = sc.nextLine(); 
            }    
        }       
        if(resposta.equals("F")||resposta.equals("f")){
            cuenta = forcaBrutaRotX(corregirPalabra(paraula));
            System.out.println("Aquí tens la llista de la desxifració forçosa: " );
            for (int x=1 ; x < cuenta.length ; x++){
                System.out.println("Desxifrat amb desplaçament "+x + ": "+cuenta[x]);
            }
        }else{
            System.out.println(respostaRot(paraula,resposta));
        }
        sc.close();
    }


    public static String xifraRotX( String cadenaEntrada, int desplaçament ){
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


    public static String desxifraRotX (String cadenaEntrada, int desplaçament ){
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

    public static String[] forcaBrutaRotX(String cadena){
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
    
    public static boolean comprova(String resposta){
        if (!resposta.equals("X") && !resposta.equals("x") && !resposta.equals("D") && !resposta.equals("d") && !resposta.equals("F") && !resposta.equals("f")){
            return false;
        }else{
            return true;
        }
    }

    public static String respostaRot(String paraula,String resposta){
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


    public static boolean comprovaNombre(int nombre){
        if (nombre>lletres.length){
            return false;
        }else{
            return true;
        }
    }

    public static String corregirPalabra(String entrada){
        String palabraCorregida = "" ;
        for (int i = 0 ; i < entrada.length() ; i++){
            if (entrada.charAt(i) == 'à'){
                palabraCorregida = palabraCorregida + 'a';
            }else if (entrada.charAt(i) == 'á'){
                palabraCorregida = palabraCorregida + "a";
            }else if (entrada.charAt(i) == 'í'){
                palabraCorregida = palabraCorregida + "i";
            }else if (entrada.charAt(i) == 'ï'){
                palabraCorregida = palabraCorregida + "i";
            }else if (entrada.charAt(i) == 'ú'){
                palabraCorregida = palabraCorregida + "u";
            }else if (entrada.charAt(i) == 'ò'){
                palabraCorregida = palabraCorregida + "o";
            }else if (entrada.charAt(i) == 'ó'){
                palabraCorregida = palabraCorregida + "o";
            }else if (entrada.charAt(i) == 'é'){
                palabraCorregida = palabraCorregida + "e";
            }else if (entrada.charAt(i) == 'è'){
                palabraCorregida = palabraCorregida + "e";
            }else if (entrada.charAt(i) == 'ü'){
                palabraCorregida = palabraCorregida + "u";
            }else if (entrada.charAt(i) == 'À'){
                palabraCorregida = palabraCorregida + "A";
            }else if (entrada.charAt(i) == 'Á'){
                palabraCorregida = palabraCorregida + "A";
            }else if (entrada.charAt(i) == 'Í'){
                palabraCorregida = palabraCorregida + "O";
            }else if (entrada.charAt(i) == 'Ï'){
                palabraCorregida = palabraCorregida + "I";
            }else if (entrada.charAt(i) == 'Ú'){
                palabraCorregida = palabraCorregida + "U";
            }else if (entrada.charAt(i) == 'Ü'){
                palabraCorregida = palabraCorregida + "U";
            }else if (entrada.charAt(i) == 'Ò'){
                palabraCorregida = palabraCorregida + "O";
            }else if (entrada.charAt(i) == 'Ó'){
                palabraCorregida = palabraCorregida + "O";
            }else if (entrada.charAt(i) == 'É'){
                palabraCorregida = palabraCorregida + "E";
            }else if (entrada.charAt(i) == 'È'){
                palabraCorregida = palabraCorregida + "E";
            }else{
                palabraCorregida = palabraCorregida + entrada.charAt(i);
            }
        }
        return palabraCorregida;
    }

}
