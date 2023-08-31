package blockchain.util;

import blockchain.model.Block;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class HashUtil {

    private final StringUtil stringUtil;

    private final Random random;

    public HashUtil() {
        this.stringUtil = new StringUtil();
        this.random = new Random();
    }

    public void generateHash(Block block, int numOfZeros) {
        String strForHash = getStringForHash(block);

        String zeros = "0".repeat(numOfZeros);
        String hash = stringUtil.applySha256(strForHash);
        long magicNumber = 0;
        Instant before = Instant.now();
        while(!hash.startsWith(zeros)) {
            hash = strForHash;
            magicNumber = random.nextLong(100000000);
            hash = stringUtil.applySha256(hash + magicNumber);
        }
        Instant after = Instant.now();
        long seconds = ChronoUnit.SECONDS.between(before, after);
        addHashInfo(block, hash, magicNumber, seconds);
    }

    private String getStringForHash(Block block) {
        return block.getId() +
                block.getCreator().toString() +
                block.getTimestamp() +
                block.getPreviousHash() +
                block.getReward().toString();
    }

    private void addHashInfo(Block block, String hash, long magicNumber, long seconds) {
        block.setHash(hash);
        block.setMagicNumber(magicNumber);
        block.setGeneratingTime(seconds);
    }
}
