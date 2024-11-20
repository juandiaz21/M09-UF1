import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class Hashes {

    private int npass;

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException{
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
    public String getPBKDF2AmbSalt(String pw, String salt)  throws NoSuchAlgorithmException, InvalidKeySpecException{
        try {
            int iteracions = 10000;
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
        String charset = "abcdefABCDEF1234567890!"; 
        char[] password = new char[6];  
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charset.length(); i++) {
            password[0] = charset.charAt(i); 
            for (int j = 0; j < charset.length(); j++) {
                password[1] = charset.charAt(j); 
                for (int k = 0; k < charset.length(); k++) {
                    password[2] = charset.charAt(k); 
                    for (int l = 0; l < charset.length(); l++) {
                        password[3] = charset.charAt(l); 
                        for (int m = 0; m < charset.length(); m++) {
                            password[4] = charset.charAt(m); 
                            for (int n = 0; n < charset.length(); n++) {
                                password[5] = charset.charAt(n); 
                                String attempt = new String(password);  
                                npass++; 
                                
                                String generatedHash = (alg.equals("SHA-512")) ? 
                                        getSHA512AmbSalt(attempt, salt) : getPBKDF2AmbSalt(attempt, salt); 
                                        
                                if (generatedHash != null && generatedHash.equals(hash)) {
                                    return attempt; 
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
        long diferencia = t2-t1;
        return diferencia + "ms";

    }
    public static void main(String[] args) throws Exception{
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
