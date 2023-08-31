package blockchain.model;


public class Block {

    private Miner creator;
    private Transaction reward;
    private long id;
    private long timestamp;
    private String previousHash;
    private String hash;
    private long magicNumber;
    private long generatingTime;
    private String blockchainAction;
    private long highestMessageId;
    private String blockData;

    public Block(Miner creator, long id, long timestamp, String previousHash, Transaction reward) {
        this.creator = creator;
        this.id = id;
        this.reward = reward;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public long getGeneratingTime() {
        return generatingTime;
    }

    public void setGeneratingTime(long generatingTime) {
        this.generatingTime = generatingTime;
    }

    public Miner getCreator() {
        return creator;
    }

    public void setCreator(Miner creator) {
        this.creator = creator;
    }

    public String getBlockchainAction() {
        return blockchainAction;
    }

    public void setBlockchainAction(String blockchainAction) {
        this.blockchainAction = blockchainAction;
    }

    public String getBlockData() {
        return blockData;
    }

    public void setBlockData(String blockData) {
        this.blockData = blockData;
    }

    public long getHighestMessageId() {
        return highestMessageId;
    }

    public void setHighestMessageId(long highestMessageId) {
        this.highestMessageId = highestMessageId;
    }

    public Transaction getReward() {
        return reward;
    }

    public void setReward(Transaction reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Block: \n");
        builder.append("Created by ").append(creator).append("\n");
        builder.append(reward.toString()).append("\n");
        builder.append("Id: ").append(id).append("\n");
        builder.append("Timestamp: ").append(timestamp).append("\n");
        builder.append("Magic number: ").append(magicNumber).append("\n");
        builder.append("Hash of the previous block: \n");
        builder.append(previousHash).append("\n");
        builder.append("Hash of the block: \n");
        builder.append(hash).append("\n");
        builder.append("Block data: ").append(blockData).append("\n");
        builder.append("Block was generating for ").append(generatingTime).append(" seconds\n");
        builder.append(blockchainAction);
        return builder.toString();
    }
}
