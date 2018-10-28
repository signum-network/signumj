package burst.kit.entity.response;

@SuppressWarnings("unused")
public abstract class BRSResponse {
    private String errorDescription;
    private Integer errorCode;
    private Integer requestProcessingTime;

    BRSResponse() {}

    public boolean hasError() {
        return errorDescription != null;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getRequestProcessingTime() {
        return requestProcessingTime;
    }
}
