package blockchain.services;

import blockchain.model.Miner;
import blockchain.model.User;

import java.util.List;

public class MinerService extends Service {

    public MinerService(int amount) {
        super(amount);
    }

    @Override
    public void createFromList(List<User> users) {
        users.forEach(v -> getWorkers().add(new Thread((Miner) v)));
    }
}
