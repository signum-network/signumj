package burst.kit.entity.response;

import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.SuggestFeeResponse;
import burst.kit.service.impl.grpc.BrsApi;

public class FeeSuggestion {
    private final BurstValue cheapFee;
    private final BurstValue standardFee;
    private final BurstValue priorityFee;

    public FeeSuggestion(BurstValue cheapFee, BurstValue standardFee, BurstValue priorityFee) {
        this.cheapFee = cheapFee;
        this.standardFee = standardFee;
        this.priorityFee = priorityFee;
    }

    public FeeSuggestion(SuggestFeeResponse suggestFeeResponse) {
        this.cheapFee = BurstValue.fromPlanck(suggestFeeResponse.getCheap());
        this.standardFee = BurstValue.fromPlanck(suggestFeeResponse.getStandard());
        this.priorityFee = BurstValue.fromPlanck(suggestFeeResponse.getPriority());
    }

    public FeeSuggestion(BrsApi.FeeSuggestion feeSuggestion) {
        this.cheapFee = BurstValue.fromPlanck(feeSuggestion.getCheap());
        this.standardFee = BurstValue.fromPlanck(feeSuggestion.getStandard());
        this.priorityFee = BurstValue.fromPlanck(feeSuggestion.getPriority());
    }

    public BurstValue getCheapFee() {
        return cheapFee;
    }

    public BurstValue getStandardFee() {
        return standardFee;
    }

    public BurstValue getPriorityFee() {
        return priorityFee;
    }
}
