package blockchain.services;

import blockchain.model.Block;
import blockchain.model.Blockchain;
import blockchain.model.Transaction;
import blockchain.model.User;
import blockchain.util.HashUtil;
import blockchain.util.Operation;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.LinkedList;

public class Miner extends User implements Runnable {

    private final int number;
    private final Blockchain blockchain;
    private final HashUtil hashUtil;
    private long blocksToMine;

    public Miner(int number, Blockchain blockchain, long blocksToMine, PrivateKey privateKey, PublicKey publicKey) {
        super("miner # " + number, privateKey, publicKey, 100);
        this.number = number;
        this.blockchain = blockchain;
        this.hashUtil = new HashUtil();
        this.blocksToMine = blocksToMine;
    }


    @Override
    public void run() {
        mine();
    }

    private void mine() {
        for (int i = 0; i < blocksToMine; i++) {
            Block block = generateBlock();
            blockchain.addBlock(block);
        }
    }

    public long getBlocksToMine() {
        return blocksToMine;
    }

    public void setBlocksToMine(long blocksToMine) {
        this.blocksToMine = blocksToMine;
    }

    private Block generateBlock() {
        LinkedList<Block> blocks = blockchain.getBlocks();
        int numOfZeros = blockchain.getNumOfZeros();
        int id = blocks.size();
        String previousHash = blocks.isEmpty() ? "0" : blocks.getLast().getHash();
        long timeStamp = Instant.now().toEpochMilli();
        Transaction reward = new Transaction(null, this, 100, Operation.GET);
        Block block = new Block(this, id, timeStamp, previousHash, reward);
        hashUtil.generateHash(block, numOfZeros);
        return block;
    }

    @Override
    public String toString() {
        return "miner" + number;
    }
}
