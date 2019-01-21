package burst.kit.entity.response;

@SuppressWarnings("unused")
public class SubmitNonceResponse extends BRSResponse {
    private String result;
    private Long deadline;

    private SubmitNonceResponse() {}

    public String getResult() {
        return result;
    }

    public Long getDeadline() {
        return deadline;
    }
}
