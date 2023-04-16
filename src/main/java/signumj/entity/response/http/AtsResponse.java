package signumj.entity.response.http;

public final class AtsResponse extends BRSResponse {
    private final ATResponse[] ats;

    public AtsResponse(ATResponse[] ats) {
        this.ats = ats;
    }

    public ATResponse[] getAts() {
        return ats;
    }
}
