package blockchain.model;

import blockchain.security.KeyGenerator;
import blockchain.services.TransactionManager;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Blockchain {

    private final LinkedList<Block> blocks;
    private final AtomicInteger numOfZeros;
    private static Blockchain blockchain;
    private final List<Transaction> blockData;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private TransactionManager transactionManager;
    private Blockchain() {
        this.blocks = new LinkedList<>();
        this.numOfZeros = new AtomicInteger(0);
        this.blockData = Collections.synchronizedList(new ArrayList<>());
        KeyGenerator keyGenerator = new KeyGenerator(512);
        keyGenerator.createKeys();
        privateKey = keyGenerator.getPrivateKey();
        publicKey = keyGenerator.getPublicKey();
    }

    public static Blockchain getInstance() {
        if (blockchain == null) {
            blockchain = new Blockchain();
        }
        return blockchain;
    }
    private boolean validate(Block newBlock) {
        if (blocks.isEmpty()) {
            return true;
        }
        String zeros = "0".repeat(numOfZeros.get());
        if (!newBlock.getHash().startsWith(zeros)) {
            return false;
        }
        if (!blocks.getLast().getHash().equals(newBlock.getPreviousHash())) {
            return false;
        }
        if (blocks.getLast().getHighestMessageId() > newBlock.getHighestMessageId()) {
            return false;
        }
        return true;
    }

    public synchronized boolean addBlock(Block block) {
        block.setHighestMessageId(getHighestId());
        if (validate(block)) {
            String action = manageZerosAndGetAction(block.getGeneratingTime());
            block.setBlockchainAction(action);
            transactionManager.processTransactionList(blockData);
            transactionManager.processTransaction(block.getReward());
            block.setBlockData(formatBlockData());
            blockData.clear();
            blocks.add(block);
            return true;
        }
        return false;
    }

    private String formatBlockData() {
        if (blockData.isEmpty()) {
            return "\nNo transactions";
        }
        return "\n" + blockData
                .stream()
                .map(Transaction::toString)
                .collect(Collectors.joining("\n"));
    }

    private String manageZerosAndGetAction(long time) {
        if (time < 0) {
            return "N was increased to " + numOfZeros.incrementAndGet();
        } else if (time > 1) {
            if (numOfZeros.get() > 0) {
                return "N was decreased to " + numOfZeros.decrementAndGet();
            }
        }
        return "N stays the same";
    }

    private long getHighestId() {
        return blockData.stream().mapToLong(Transaction::getId).max().orElse(0);
    }

    public synchronized void addData(Transaction data) {
        blockData.add(data);
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public int getNumOfZeros() {
        return numOfZeros.get();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public String printWithLimit(int limit) {
        return blocks
                .stream()
                .map(Block::toString)
                .limit(limit)
                .collect(Collectors.joining("\n\n"));
    }
}
