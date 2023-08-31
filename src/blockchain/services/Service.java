package blockchain.services;


import blockchain.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Service {

    private final List<Thread> workers;
    private final int amount;

    protected Service(int amount) {
        this.amount = amount;
        this.workers = new ArrayList<>();
    }

    public abstract void createFromList(List<User> users);
    public void clear() {
        workers.clear();
    }
    public List<Thread> getWorkers() {
        return workers;
    }

    public int getAmount() {
        return amount;
    }
}
