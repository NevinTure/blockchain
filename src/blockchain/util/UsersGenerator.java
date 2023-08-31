package blockchain.util;

import blockchain.model.Blockchain;
import blockchain.model.User;
import blockchain.security.KeyGenerator;
import blockchain.services.Miner;

import java.util.*;

public class UsersGenerator {

    public static List<User> generateSimpleUsers(int amount) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            String name = "User # " + i;
            KeyGenerator keyGenerator = new KeyGenerator(512);
            keyGenerator.createKeys();
            User user = new User(name, keyGenerator.getPrivateKey(), keyGenerator.getPublicKey(), 100);
            users.add(user);
        }
        return users;
    }

    public static List<User> generateMiners(int amount, Blockchain blockchain) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            KeyGenerator keyGenerator = new KeyGenerator(512);
            keyGenerator.createKeys();
            User user =
                        new Miner(i + 1, blockchain, 10000, keyGenerator.getPrivateKey(), keyGenerator.getPublicKey());
            users.add(user);
        }
        return users;
    }
}
