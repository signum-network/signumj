package burst.kit.entity.response;

@SuppressWarnings("unused")
public class AccountATsResponse extends BRSResponse {
    private ATResponse[] ats;

    private AccountATsResponse() {}

    public ATResponse[] getATs() {
        return ats;
    }
}
