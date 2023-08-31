package blockchain.view;

import blockchain.model.Blockchain;
import blockchain.model.Transaction;
import blockchain.model.User;
import blockchain.security.SignatureManager;
import java.util.concurrent.atomic.AtomicLong;

public class HandlerCenter {

    private final SignatureManager signManager;
    private final AtomicLong idAccumulator;
    private final Blockchain blockchain;

    public HandlerCenter(Blockchain blockchain) {
        this.blockchain = blockchain;
        signManager = new SignatureManager();
        idAccumulator = new AtomicLong(1);
    }

    public void makeTransaction(Transaction transaction) {
        switch (transaction.getOperation()) {
            case SEND -> {
                if (checkTransaction(transaction)) {
                    User sender = transaction.getSender();
                    transaction.setSignature(
                            signManager.getSignature(transaction.toString(),
                                    sender.getPrivateKey())
                    );
                    confirmTransaction(transaction);
                }
            }
            case GET -> {
                transaction.setSignature(
                        signManager.getSignature(transaction.toString(),
                                blockchain.getPrivateKey())
                );
                confirmTransaction(transaction);
            }
        }
    }

    private void confirmTransaction(Transaction transaction) {
        transaction.setId(idAccumulator.getAndIncrement());
        blockchain.addData(transaction);
    }

    private boolean checkTransaction(Transaction transaction) {
        User sender = transaction.getSender();
        return sender.getCoins() > transaction.getAmount();
    }
}
