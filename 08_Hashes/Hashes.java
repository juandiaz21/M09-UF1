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
            int clauLength = 128; 
            char[] pwChar = pw.toCharArray();
            byte[] saltBytes = salt.getBytes();

            PBEKeySpec spec = new PBEKeySpec(pwChar, saltBytes, iteracions, clauLength);
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

        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        npass = 0;

        for (int len = 1; len <= 6; len++) {

            char[] attempt = new char[len];

            for (int i = 0; i < charset.length; i++) {
                attempt[0] = charset[i];
                if (len == 1 && checkPassword(attempt, alg, hash, salt)) return new String(attempt);

                for (int j = 0; j < charset.length; j++) {
                    if (len > 1) attempt[1] = charset[j];
                    if (len == 2 && checkPassword(attempt, alg, hash, salt)) return new String(attempt);

                    for (int k = 0; k < charset.length; k++) {
                        if (len > 2) attempt[2] = charset[k];
                        if (len == 3 && checkPassword(attempt, alg, hash, salt)) return new String(attempt);

                        for (int l = 0; l < charset.length; l++) {
                            if (len > 3) attempt[3] = charset[l];
                            if (len == 4 && checkPassword(attempt, alg, hash, salt)) return new String(attempt);

                            for (int m = 0; m < charset.length; m++) {
                                if (len > 4) attempt[4] = charset[m];
                                if (len == 5 && checkPassword(attempt, alg, hash, salt)) return new String(attempt);

                                for (int n = 0; n < charset.length; n++) {
                                    if (len > 5) attempt[5] = charset[n];
                                    if (len == 6 && checkPassword(attempt, alg, hash, salt)) return new String(attempt);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null; 
    }

    private boolean checkPassword(char[] attempt, String alg, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
        npass++;
        String generatedHash;
        String passwordAttempt = new String(attempt);
        
        if ("SHA-512".equals(alg)) {
            generatedHash = getSHA512AmbSalt(passwordAttempt, salt);
        } else if ("PBKDF2".equals(alg)) {
            generatedHash = getPBKDF2AmbSalt(passwordAttempt, salt);
        } else {
            return false;
        }
        return hash.equals(generatedHash);
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
