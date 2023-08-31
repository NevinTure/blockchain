package blockchain.security;

import java.security.*;

public class SignatureManager {

    private Signature rsa;

    public SignatureManager() {
        try {
            this.rsa = Signature.getInstance("SHA1withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public byte[] getSignature(String messageStr, PrivateKey privateKey) {
        try {
            rsa.initSign(privateKey);
            rsa.update(messageStr.getBytes());
            return rsa.sign();
        } catch (SignatureException | InvalidKeyException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public boolean verifyMessageWithSignature(String messageStr, byte[] sign, PublicKey publicKey) {
        try {
            rsa.initVerify(publicKey);
            rsa.update(messageStr.getBytes());
            return rsa.verify(sign);
        } catch (SignatureException | InvalidKeyException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

}
