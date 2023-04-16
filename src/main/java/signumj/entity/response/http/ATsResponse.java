package signumj.entity.response.http;

public final class ATsResponse extends BRSResponse {
    private final ATResponse[] ats;

    public ATsResponse(ATResponse[] ats) {
        this.ats = ats;
    }

    public ATResponse[] getATs() {
        return ats;
    }
}
