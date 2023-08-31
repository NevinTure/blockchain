package blockchain.model;

import blockchain.util.Operation;
import blockchain.view.HandlerCenter;


import java.util.List;
import java.util.Random;

public class Messenger implements Runnable {

    private final HandlerCenter handlerCenter;
    private final long delay;
    private final User user;
    private List<User> users;

    public Messenger(HandlerCenter handlerCenter, long delay, User user, List<User> users) {
        this.handlerCenter = handlerCenter;
        this.delay = delay;
        this.user = user;
        this.users = users;
    }


    @Override
    public void run() {
        generateRandomMessage();
    }

    private void generateRandomMessage() {
        Random random = new Random();
        try {
            long time = 0;
            while ((time += delay) < 5000) {
                User userToSend = users.get(random.nextInt(users.size()));
                Transaction transaction =
                        new Transaction(user, userToSend, random.nextLong(1, 20), Operation.SEND);
                handlerCenter.makeTransaction(transaction);
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
