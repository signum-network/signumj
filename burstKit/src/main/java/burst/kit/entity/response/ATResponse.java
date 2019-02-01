package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.HexStringByteArray;
import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public final class ATResponse extends BRSResponse {
    private final int atVersion;
    private final BurstAddress creator;
    private final boolean stopped;
    private final HexStringByteArray machineCode;
    private final String description;
    private final HexStringByteArray machineData;
    private final boolean frozen;
    private final boolean finished;
    private final boolean dead;
    private final BurstValue balanceNQT;
    private final int nextBlock;
    private final BurstValue minActivation;
    private final boolean running;
    private final BurstAddress at;
    private final BurstAddress atRS;
    private final String name;
    private final BurstValue prevBalanceNQT;
    private final int creationBlock;

    public ATResponse(int atVersion, BurstAddress creator, boolean stopped, HexStringByteArray machineCode, String description, HexStringByteArray machineData, boolean frozen, boolean finished, boolean dead, BurstValue balanceNQT, int nextBlock, BurstValue minActivation, boolean running, BurstAddress at, BurstAddress atRS, String name, BurstValue prevBalanceNQT, int creationBlock) {
        this.atVersion = atVersion;
        this.creator = creator;
        this.stopped = stopped;
        this.machineCode = machineCode;
        this.description = description;
        this.machineData = machineData;
        this.frozen = frozen;
        this.finished = finished;
        this.dead = dead;
        this.balanceNQT = balanceNQT;
        this.nextBlock = nextBlock;
        this.minActivation = minActivation;
        this.running = running;
        this.at = at;
        this.atRS = atRS;
        this.name = name;
        this.prevBalanceNQT = prevBalanceNQT;
        this.creationBlock = creationBlock;
    }

    public int getAtVersion() {
        return atVersion;
    }

    public BurstAddress getCreator() {
        return creator;
    }

    public boolean isStopped() {
        return stopped;
    }

    public HexStringByteArray getMachineCode() {
        return machineCode;
    }

    public String getDescription() {
        return description;
    }

    public HexStringByteArray getMachineData() {
        return machineData;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isDead() {
        return dead;
    }

    public BurstValue getBalanceNQT() {
        return balanceNQT;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public BurstValue getMinActivation() {
        return minActivation;
    }

    public boolean isRunning() {
        return running;
    }

    public BurstAddress getAt() {
        return atRS;
    }

    public String getName() {
        return name;
    }

    public BurstValue getPrevBalanceNQT() {
        return prevBalanceNQT;
    }

    public int getCreationBlock() {
        return creationBlock;
    }
}
