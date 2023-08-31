package blockchain.model;

import blockchain.util.Operation;

public class Transaction {

    private byte[] signature;
    private long id;
    private final User sender;
    private final User receiver;
    private final long amount;
    private final Operation operation;

    public Transaction(User sender, User receiver, long amount, Operation operation) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.operation = operation;
    }

    public byte[] getSignature() {
        return signature;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Operation getOperation() {
        return operation;
    }

    public long getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return switch (operation) {
            case GET -> "%s gets %s VC".formatted(receiver, amount);
            case SEND -> "%s send %d VC to %s".formatted(sender, amount, receiver);
        };
    }
}
