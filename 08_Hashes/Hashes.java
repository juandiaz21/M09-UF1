import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class Hashes {

    private int npass; //nombre d'intents

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException{ //metode fer hash amb SHA-512
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] hashBytes = md.digest(pw.getBytes());
            HexFormat hex = HexFormat.of();
            String hash = hex.formatHex(hashBytes);
            return hash;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
    public String getPBKDF2AmbSalt(String pw, String salt)  throws NoSuchAlgorithmException, InvalidKeySpecException{ //metode fer hash amb PBKDF2
        try {
            int iteracions = 65536;
            int claulength = 128; 
            char[] pwChar = pw.toCharArray();
            byte[] saltBytes = salt.getBytes();

            PBEKeySpec spec = new PBEKeySpec(pwChar, saltBytes, iteracions, claulength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHa512");
            byte[] hashedBytes = keyFactory.generateSecret(spec).getEncoded();

            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashedBytes);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String forcaBruta(String alg, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String charset = "abcdefABCDEF1234567890!"; //els chars que provarem
        char[] pw = new char[6];  //l'array amb el que crearem els strings a comparar
        for (int i = 0; i < charset.length(); i++) { //bucles que faran que es provi cada char 
            pw[0] = charset.charAt(i); 
            for (int j = 0; j < charset.length(); j++) {
                pw[1] = charset.charAt(j); 
                for (int k = 0; k < charset.length(); k++) {
                    pw[2] = charset.charAt(k); 
                    for (int l = 0; l < charset.length(); l++) {
                        pw[3] = charset.charAt(l); 
                        for (int m = 0; m < charset.length(); m++) {
                            pw[4] = charset.charAt(m); 
                            for (int n = 0; n < charset.length(); n++) {
                                pw[5] = charset.charAt(n); 
                                String prova = new String(pw); //creem el string que provarem 
                                npass++; //nou intent
                                String generatedHash; 
                                if (alg.equals("SHA-512")){ //mirem quin alg estem utiliztan
                                    generatedHash = getSHA512AmbSalt(prova, salt); //fem el hash amb el string i salt 
                                }
                                else{
                                    generatedHash = getPBKDF2AmbSalt(prova, salt);
                                }    
                                if (generatedHash != null && generatedHash.equals(hash)) { //comparem els hashes
                                    return prova; //tornem el pw desxifrat
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return null; 
    }

    public String getInterval(long t1, long t2){ 
        long diferencia = t2-t1; //calcul del temps trigat
        return diferencia + "ms";

    }
    public static void main(String[] args) throws Exception{ //main de l'activitat
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            
            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }

}
