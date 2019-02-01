package burst.kit.entity.response;

import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public class SuggestFeeResponse extends BRSResponse {
    private final BurstValue cheap;
    private final BurstValue standard;
    private final BurstValue priority;

    public SuggestFeeResponse(BurstValue cheap, BurstValue standard, BurstValue priority) {
        this.cheap = cheap;
        this.standard = standard;
        this.priority = priority;
    }

    public BurstValue getCheap() {
        return cheap;
    }

    public BurstValue getStandard() {
        return standard;
    }

    public BurstValue getPriority() {
        return priority;
    }
}
