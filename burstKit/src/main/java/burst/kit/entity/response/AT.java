package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.ATResponse;
import org.bouncycastle.util.encoders.Hex;

public class AT {
    private final boolean dead;
    private final boolean finished;
    private final boolean frozen;
    private final boolean running;
    private final boolean stopped;
    private final BurstAddress creator;
    private final BurstID id;
    private final BurstValue balance;
    private final BurstValue minimumActivation;
    private final BurstValue previousBalance;
    private final byte[] machineCode;
    private final byte[] machineData;
    private final int creationHeight;
    private final int nextBlockHeight;
    private final int version;
    private final String description;
    private final String name;

    public AT(boolean dead, boolean finished, boolean frozen, boolean running, boolean stopped, BurstAddress creator, BurstID id, BurstValue balance, BurstValue minimumActivation, BurstValue previousBalance, byte[] machineCode, byte[] machineData, int creationHeight, int nextBlockHeight, int version, String description, String name) {
        this.dead = dead;
        this.finished = finished;
        this.frozen = frozen;
        this.running = running;
        this.stopped = stopped;
        this.creator = creator;
        this.id = id;
        this.balance = balance;
        this.minimumActivation = minimumActivation;
        this.previousBalance = previousBalance;
        this.machineCode = machineCode;
        this.machineData = machineData;
        this.creationHeight = creationHeight;
        this.nextBlockHeight = nextBlockHeight;
        this.version = version;
        this.description = description;
        this.name = name;
    }

    public AT(ATResponse atResponse) {
        this.dead = atResponse.isDead();
        this.finished = atResponse.isFinished();
        this.frozen = atResponse.isFrozen();
        this.running = atResponse.isRunning();
        this.stopped = atResponse.isStopped();
        this.creator = BurstAddress.fromEither(atResponse.getCreator());
        this.id = BurstID.fromLong(atResponse.getAt());
        this.balance = BurstValue.fromPlanck(atResponse.getBalanceNQT());
        this.minimumActivation = BurstValue.fromPlanck(atResponse.getMinActivation());
        this.previousBalance = BurstValue.fromPlanck(atResponse.getPrevBalanceNQT());
        this.machineCode = Hex.decode(atResponse.getMachineCode());
        this.machineData = Hex.decode(atResponse.getMachineData());
        this.creationHeight = atResponse.getCreationBlock();
        this.nextBlockHeight = atResponse.getNextBlock();
        this.version = atResponse.getAtVersion();
        this.description = atResponse.getDescription();
        this.name = atResponse.getName();
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStopped() {
        return stopped;
    }

    public BurstAddress getCreator() {
        return creator;
    }

    public BurstID getId() {
        return id;
    }

    public BurstValue getBalance() {
        return balance;
    }

    public BurstValue getMinimumActivation() {
        return minimumActivation;
    }

    public BurstValue getPreviousBalance() {
        return previousBalance;
    }

    public byte[] getMachineCode() {
        return machineCode;
    }

    public byte[] getMachineData() {
        return machineData;
    }

    public int getCreationHeight() {
        return creationHeight;
    }

    public int getNextBlockHeight() {
        return nextBlockHeight;
    }

    public int getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
