package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.http.BlockResponse;

import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.Arrays;

public class Block {
    private final BigInteger nonce;
    private final SignumAddress generator;
    private final SignumID id;
    private final SignumID nextBlock;
    private final SignumID previousBlock;
    private final SignumID[] transactions;
    private final SignumTimestamp timestamp;
    private final SignumValue blockReward;
    private final SignumValue totalAmount;
    private final SignumValue totalFee;
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
    private final long averageCommitmentNQT;

    public Block(BigInteger nonce, SignumAddress generator, SignumID id, SignumID nextBlock, SignumID previousBlock, SignumID[] transactions, SignumTimestamp timestamp, SignumValue blockReward, SignumValue totalAmount, SignumValue totalFee, byte[] generationSignature, byte[] generatorPublicKey, byte[] payloadHash, byte[] previousBlockHash, byte[] signature, int height, int payloadLength, int scoopNum, int version, long baseTarget, long averageCommitmentNQT) {
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
        this.averageCommitmentNQT = averageCommitmentNQT;
    }

    public Block(BlockResponse blockResponse) {
        this.nonce = new BigInteger(blockResponse.getNonce());
        this.generator = SignumAddress.fromEither(blockResponse.getGenerator());
        this.id = SignumID.fromLong(blockResponse.getBlock());
        this.nextBlock = SignumID.fromLong(blockResponse.getNextBlock());
        this.previousBlock = SignumID.fromLong(blockResponse.getPreviousBlock());
        this.transactions = Arrays.stream(blockResponse.getTransactions())
                .map(SignumID::fromLong)
                .toArray(SignumID[]::new);
        this.timestamp = SignumTimestamp.fromBurstTimestamp(blockResponse.getTimestamp());
        this.blockReward = SignumValue.fromSigna(blockResponse.getBlockReward());
        this.totalAmount = SignumValue.fromNQT(blockResponse.getTotalAmountNQT());
        this.totalFee = SignumValue.fromNQT(blockResponse.getTotalFeeNQT());
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
        this.averageCommitmentNQT = blockResponse.getAverageCommitmentNQT();
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public SignumAddress getGenerator() {
        return generator;
    }

    public SignumID getId() {
        return id;
    }

    public SignumID getNextBlock() {
        return nextBlock;
    }

    public SignumID getPreviousBlock() {
        return previousBlock;
    }

    public SignumID[] getTransactions() {
        return transactions;
    }

    public SignumTimestamp getTimestamp() {
        return timestamp;
    }

    public SignumValue getBlockReward() {
        return blockReward;
    }

    public SignumValue getTotalAmount() {
        return totalAmount;
    }

    public SignumValue getTotalFee() {
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
    
    public long getAverageCommitmentNQT() {
		return averageCommitmentNQT;
	}
}
