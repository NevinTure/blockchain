package blockchain.services;


import blockchain.model.Messenger;
import blockchain.model.User;
import blockchain.view.HandlerCenter;

import java.util.List;

public class MessengerService extends Service {

    private final HandlerCenter handlerCenter;
    private final long delay;
    public MessengerService(int amount, HandlerCenter handlerCenter, long delay) {
        super(amount);
        this.handlerCenter = handlerCenter;
        this.delay = delay;
    }

    @Override
    public void createFromList(List<User> users) {
        users.forEach(v -> {
            Messenger messenger = new Messenger(handlerCenter, delay, v, users);
            getWorkers().add(new Thread(messenger));
        });
    }
}
