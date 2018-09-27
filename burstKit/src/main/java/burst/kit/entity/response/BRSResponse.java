package burst.kit.entity.response;

@SuppressWarnings("unused")
public class BRSResponse {
    private String errorDescription;
    private int errorCode;
    private int requestProcessingTime;

    BRSResponse() {}

    public boolean hasError() {
        return errorDescription != null;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getRequestProcessingTime() {
        return requestProcessingTime;
    }
}
