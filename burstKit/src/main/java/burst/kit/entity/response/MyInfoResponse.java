package burst.kit.entity.response;

@SuppressWarnings("unused")
public class MyInfoResponse extends BRSResponse {
    private String address;
    private String host;

    private MyInfoResponse() {}

    public String getAddress() {
        return address;
    }

    public String getHost() {
        return host;
    }
}
