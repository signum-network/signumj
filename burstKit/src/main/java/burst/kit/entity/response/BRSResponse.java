package burst.kit.entity.response;

@SuppressWarnings("unused")
public abstract class BRSResponse {
    private String errorDescription;
    private Integer errorCode;
    private Integer requestProcessingTime;

    BRSResponse() {}

    public void throwIfError() throws BRSError {
        if (errorDescription != null) {
            throw new BRSError(errorCode, errorDescription);
        }
    }

    public Integer getRequestProcessingTime() {
        return requestProcessingTime;
    }
}
