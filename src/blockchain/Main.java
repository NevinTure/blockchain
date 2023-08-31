package blockchain;

import blockchain.model.Blockchain;
import blockchain.services.MessengerService;
import blockchain.services.MinerService;
import blockchain.services.TransactionManager;
import blockchain.view.CryptoChat;
import blockchain.view.HandlerCenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Blockchain blockchain = Blockchain.getInstance();
        TransactionManager transactionManager = new TransactionManager();
        blockchain.setTransactionManager(transactionManager);
        ExecutorService executor = Executors.newFixedThreadPool(15);
        HandlerCenter handlerCenter = new HandlerCenter(blockchain);
        MinerService minerService = new MinerService(5);
        MessengerService messengerService = new MessengerService(5, handlerCenter, 50);

        CryptoChat chat = new CryptoChat(minerService, messengerService, blockchain, executor);
        chat.start();
        System.out.println(blockchain.printWithLimit(15));
    }
}
