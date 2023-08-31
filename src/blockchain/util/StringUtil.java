package blockchain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

    private final MessageDigest digest;

    public StringUtil() {
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    /* Applies Sha256 to a string and returns a hash. */
    public String applySha256(String input) {
        try {
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
