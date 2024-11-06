package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(1024); //inicialitzem el keyGenerator
        return generador.generateKeyPair();
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes("UTF-8"));// UTF-8 per accents i altres simbols
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
        return new String(msgDesxifrat, "UTF-8"); // UTF-8 per accents i altres simbols
    }
}
