package burst.kit.entity.response.http;

public class BRSError extends Exception {
    private final int code;
    private final String description;

    public BRSError(int code, String description) {
        super("BRS returned error code " + code + ": " + description);
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
