package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.HexStringByteArray;
import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public class ATResponse extends BRSResponse {
    private int atVersion;
    private BurstAddress creator;
    private boolean stopped;
    private HexStringByteArray machineCode;
    private String description;
    private HexStringByteArray machineData;
    private boolean frozen;
    private boolean finished;
    private boolean dead;
    private BurstValue balanceNQT;
    private int nextBlock;
    private BurstValue minActivation;
    private boolean running;
    private BurstAddress at;
    private BurstAddress atRS;
    private String name;
    private BurstValue prevBalanceNQT;
    private int creationBlock;

    private ATResponse() {}

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
