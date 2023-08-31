package blockchain.view;

import blockchain.model.Blockchain;
import blockchain.model.User;
import blockchain.services.MessengerService;
import blockchain.services.MinerService;
import blockchain.util.UsersGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CryptoChat {

    private final MinerService minerService;
    private final MessengerService messengerService;
    private final Blockchain blockchain;
    private final ExecutorService executor;

    public CryptoChat(
            MinerService minerService,
            MessengerService messengerService,
            Blockchain blockchain,
            ExecutorService executor) {
        this.minerService = minerService;
        this.messengerService = messengerService;
        this.blockchain = blockchain;
        this.executor = executor;
    }

    public void start() throws InterruptedException {
        List<User> simpleUsers = UsersGenerator.generateSimpleUsers(messengerService.getAmount());
        List<User> miners = UsersGenerator.generateMiners(minerService.getAmount(), blockchain);
        List<User> users = Stream.concat(simpleUsers.stream(), miners.stream()).toList();
        messengerService.createFromList(users);
        minerService.createFromList(miners);
        List<Thread> workers = new ArrayList<>(Stream
                .concat(messengerService.getWorkers().stream(), minerService.getWorkers().stream())
                .toList());
        Collections.shuffle(workers);
        for (Thread worker : workers) {
            executor.submit(worker);
        }
        executor.shutdown();
        executor.awaitTermination(15, TimeUnit.SECONDS);
    }
}
