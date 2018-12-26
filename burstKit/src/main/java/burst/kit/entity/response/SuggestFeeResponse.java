package burst.kit.entity.response;

import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public class SuggestFeeResponse extends BRSResponse {
    private BurstValue cheap;
    private BurstValue standard;
    private BurstValue priority;

    private SuggestFeeResponse() {}

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
