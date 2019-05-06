package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class ATResponse extends BRSResponse {
    private final int atVersion;
    private final String creator;
    private final boolean stopped;
    private final String machineCode;
    private final String description;
    private final String machineData;
    private final boolean frozen;
    private final boolean finished;
    private final boolean dead;
    private final String balanceNQT;
    private final int nextBlock;
    private final String minActivation;
    private final boolean running;
    private final String at;
    private final String atRS;
    private final String name;
    private final String prevBalanceNQT;
    private final int creationBlock;

    public ATResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, int atVersion, String creator, boolean stopped, String machineCode, String description, String machineData, boolean frozen, boolean finished, boolean dead, String balanceNQT, int nextBlock, String minActivation, boolean running, String at, String atRS, String name, String prevBalanceNQT, int creationBlock) {
        super(errorDescription, errorCode, requestProcessingTime);
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

    public String getCreator() {
        return creator;
    }

    public boolean isStopped() {
        return stopped;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getDescription() {
        return description;
    }

    public String getMachineData() {
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

    public String getBalanceNQT() {
        return balanceNQT;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public String getMinActivation() {
        return minActivation;
    }

    public boolean isRunning() {
        return running;
    }

    public String getAt() {
        return at;
    }

    public String getAtRS() {
        return atRS;
    }

    public String getName() {
        return name;
    }

    public String getPrevBalanceNQT() {
        return prevBalanceNQT;
    }

    public int getCreationBlock() {
        return creationBlock;
    }
}
