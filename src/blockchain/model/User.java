package blockchain.model;

import java.security.PrivateKey;
import java.security.PublicKey;

public class User {

    private String name;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private long coins;

    public User(String name, PrivateKey privateKey, PublicKey publicKey, long coins) {
        this.name = name;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.coins = coins;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
