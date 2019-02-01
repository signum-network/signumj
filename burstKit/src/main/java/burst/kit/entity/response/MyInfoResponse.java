package burst.kit.entity.response;

@SuppressWarnings("unused")
public class MyInfoResponse extends BRSResponse {
    private final String address;
    private final String host;

    public MyInfoResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String address, String host) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.address = address;
        this.host = host;
    }

    public String getAddress() {
        return address;
    }

    public String getHost() {
        return host;
    }
}
