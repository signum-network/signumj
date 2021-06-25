package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class AccountATsResponse extends BRSResponse {
    private final ATResponse[] ats;

    public AccountATsResponse(ATResponse[] ats) {
        this.ats = ats;
    }

    public ATResponse[] getATs() {
        return ats;
    }
}
