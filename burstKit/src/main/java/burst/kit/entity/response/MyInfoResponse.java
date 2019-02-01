package burst.kit.entity.response;

@SuppressWarnings("unused")
public class MyInfoResponse extends BRSResponse {
    private final String address;
    private final String host;

    public MyInfoResponse(String address, String host) {
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
