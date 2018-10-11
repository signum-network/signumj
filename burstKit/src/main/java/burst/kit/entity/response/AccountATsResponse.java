package burst.kit.entity.response;

@SuppressWarnings("unused")
public final class AccountATsResponse extends BRSResponse {
    private ATResponse[] ats;

    private AccountATsResponse() {}

    public ATResponse[] getATs() {
        return ats;
    }
}
