package burst.kit.entity.response;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.BlockResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Block {
    private final BigInteger nonce;
    private final BurstAddress generator;
    private final BurstID id;
    private final BurstID nextBlock;
    private final BurstID previousBlock;
    private final BurstID[] transactions;
    private final BurstTimestamp timestamp;
    private final BurstValue blockReward;
    private final BurstValue totalAmount;
    private final BurstValue totalFee;
    private final byte[] generationSignature;
    private final byte[] generatorPublicKey;
    private final byte[] payloadHash;
    private final byte[] previousBlockHash;
    private final byte[] signature;
    private final int height;
    private final int payloadLength;
    private final int scoopNum;
    private final int version;
    private final long baseTarget;

    public Block(BigInteger nonce, BurstAddress generator, BurstID id, BurstID nextBlock, BurstID previousBlock, BurstID[] transactions, BurstTimestamp timestamp, BurstValue blockReward, BurstValue totalAmount, BurstValue totalFee, byte[] generationSignature, byte[] generatorPublicKey, byte[] payloadHash, byte[] previousBlockHash, byte[] signature, int height, int payloadLength, int scoopNum, int version, long baseTarget) {
        this.nonce = nonce;
        this.generator = generator;
        this.id = id;
        this.nextBlock = nextBlock;
        this.previousBlock = previousBlock;
        this.transactions = transactions;
        this.timestamp = timestamp;
        this.blockReward = blockReward;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.generationSignature = generationSignature;
        this.generatorPublicKey = generatorPublicKey;
        this.payloadHash = payloadHash;
        this.previousBlockHash = previousBlockHash;
        this.signature = signature;
        this.height = height;
        this.payloadLength = payloadLength;
        this.scoopNum = scoopNum;
        this.version = version;
        this.baseTarget = baseTarget;
    }

    public Block(BlockResponse blockResponse) {
        this.nonce = new BigInteger(blockResponse.getNonce());
        this.generator = BurstAddress.fromEither(blockResponse.getGenerator());
        this.id = BurstID.fromLong(blockResponse.getBlock());
        this.nextBlock = BurstID.fromLong(blockResponse.getNextBlock());
        this.previousBlock = BurstID.fromLong(blockResponse.getPreviousBlock());
        this.transactions = Arrays.stream(blockResponse.getTransactions())
                .map(BurstID::fromLong)
                .toArray(BurstID[]::new);
        this.timestamp = new BurstTimestamp(blockResponse.getTimestamp());
        this.blockReward = BurstValue.fromBurst(blockResponse.getBlockReward());
        this.totalAmount = BurstValue.fromPlanck(blockResponse.getTotalAmountNQT());
        this.totalFee = BurstValue.fromPlanck(blockResponse.getTotalFeeNQT());
        this.generationSignature = Hex.decode(blockResponse.getGenerationSignature());
        this.generatorPublicKey = Hex.decode(blockResponse.getGeneratorPublicKey());
        this.payloadHash = Hex.decode(blockResponse.getPayloadHash());
        this.previousBlockHash = Hex.decode(blockResponse.getPreviousBlockHash());
        this.signature = Hex.decode(blockResponse.getBlockSignature());
        this.height = blockResponse.getHeight();
        this.payloadLength = blockResponse.getPayloadLength();
        this.scoopNum = blockResponse.getScoopNum();
        this.version = blockResponse.getVersion();
        this.baseTarget = blockResponse.getBaseTarget();
    }

    public Block(BrsApi.Block block) {
        BurstCrypto burstCrypto = BurstCrypto.getInstance();
        this.nonce = new BigInteger(Long.toUnsignedString(block.getNonce()));
        this.generator = burstCrypto.getBurstAddressFromPublic(block.getGeneratorPublicKey().toByteArray());
        this.id = BurstID.fromLong(block.getId());
        this.nextBlock = BurstID.fromLong(block.getNextBlockId());
        this.previousBlock = block.getPreviousBlockHash().size() == 0 ? BurstID.fromLong(0) : burstCrypto.hashToId(block.getPreviousBlockHash().toByteArray());
        this.transactions = block.getTransactionIdsList()
                .stream()
                .map(BurstID::fromLong)
                .toArray(BurstID[]::new);
        this.timestamp = new BurstTimestamp(block.getTimestamp());
        this.blockReward = BurstValue.fromPlanck(block.getBlockReward());
        this.totalAmount = BurstValue.fromPlanck(block.getTotalAmount());
        this.totalFee = BurstValue.fromPlanck(block.getTotalFee());
        this.generationSignature = block.getGenerationSignature().toByteArray();
        this.generatorPublicKey = block.getGeneratorPublicKey().toByteArray();
        this.payloadHash = block.getPayloadHash().toByteArray();
        this.previousBlockHash = block.getPreviousBlockHash().toByteArray();
        this.signature = block.getBlockSignature().toByteArray();
        this.height = block.getHeight();
        this.payloadLength = block.getPayloadLength();
        this.scoopNum = block.getScoop();
        this.version = block.getVersion();
        this.baseTarget = block.getBaseTarget();
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public BurstAddress getGenerator() {
        return generator;
    }

    public BurstID getId() {
        return id;
    }

    public BurstID getNextBlock() {
        return nextBlock;
    }

    public BurstID getPreviousBlock() {
        return previousBlock;
    }

    public BurstID[] getTransactions() {
        return transactions;
    }

    public BurstTimestamp getTimestamp() {
        return timestamp;
    }

    public BurstValue getBlockReward() {
        return blockReward;
    }

    public BurstValue getTotalAmount() {
        return totalAmount;
    }

    public BurstValue getTotalFee() {
        return totalFee;
    }

    public byte[] getGenerationSignature() {
        return generationSignature;
    }

    public byte[] getGeneratorPublicKey() {
        return generatorPublicKey;
    }

    public byte[] getPayloadHash() {
        return payloadHash;
    }

    public byte[] getPreviousBlockHash() {
        return previousBlockHash;
    }

    public byte[] getSignature() {
        return signature;
    }

    public int getHeight() {
        return height;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public int getScoopNum() {
        return scoopNum;
    }

    public int getVersion() {
        return version;
    }

    public long getBaseTarget() {
        return baseTarget;
    }
}
