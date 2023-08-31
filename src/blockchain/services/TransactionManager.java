package blockchain.services;

import blockchain.model.Transaction;
import blockchain.model.User;

import java.util.List;

public class TransactionManager {

    public void processTransactionList(List<Transaction> transactions) {
        for(Transaction transaction : transactions) {
            processTransaction(transaction);
        }
    }

    public void processTransaction(Transaction transaction) {
        switch (transaction.getOperation()) {
            case SEND -> send(transaction);
            case GET -> get(transaction);
        }
    }

    private void get(Transaction transaction) {
        User receiver = transaction.getReceiver();
        receiver.setCoins(receiver.getCoins() + transaction.getAmount());
    }
    private void send(Transaction transaction) {
        User sender = transaction.getSender();
        User receiver = transaction.getReceiver();
        receiver.setCoins(receiver.getCoins() + transaction.getAmount());
        sender.setCoins(receiver.getCoins() - transaction.getAmount());
    }
}
