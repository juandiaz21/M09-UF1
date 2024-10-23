package iticbcn.xifratge; 
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ActivitatCinc";

    public byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] msgBytes = msg.getBytes();

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = sha.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] xifrat = cipher.doFinal(msgBytes);

        byte[] combined = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(xifrat, 0, combined, iv.length, xifrat.length);

        return combined;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        byte[] ivExtraido = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtraido);

        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = sha.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat);
    }

}