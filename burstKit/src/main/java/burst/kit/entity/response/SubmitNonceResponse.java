package burst.kit.entity.response;

@SuppressWarnings("unused")
public class SubmitNonceResponse extends BRSResponse {
    private final String result;
    private final Long deadline;

    public SubmitNonceResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String result, Long deadline) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.result = result;
        this.deadline = deadline;
    }

    public String getResult() {
        return result;
    }

    public Long getDeadline() {
        return deadline;
    }
}
