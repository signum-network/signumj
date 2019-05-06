package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public class SuggestFeeResponse extends BRSResponse {
    private final String cheap;
    private final String standard;
    private final String priority;

    public SuggestFeeResponse(String cheap, String standard, String priority) {
        this.cheap = cheap;
        this.standard = standard;
        this.priority = priority;
    }

    public String getCheap() {
        return cheap;
    }

    public String getStandard() {
        return standard;
    }

    public String getPriority() {
        return priority;
    }
}
