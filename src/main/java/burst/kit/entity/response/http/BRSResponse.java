package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public abstract class BRSResponse {
    private final String errorDescription;
    private final Integer errorCode;
    private final Integer requestProcessingTime;

    public BRSResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime) {
        this.errorDescription = errorDescription;
        this.errorCode = errorCode;
        this.requestProcessingTime = requestProcessingTime;
    }

    public BRSResponse() {
        this.errorDescription = null;
        this.errorCode = null;
        this.requestProcessingTime = null;
    }

    public void throwIfError() throws BRSError {
        if (errorDescription != null) {
            throw new BRSError(errorCode, errorDescription);
        }
    }

    public Integer getRequestProcessingTime() {
        return requestProcessingTime;
    }
}
