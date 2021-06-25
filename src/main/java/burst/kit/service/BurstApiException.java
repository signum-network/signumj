package burst.kit.service;

public class BurstApiException extends RuntimeException {
    public BurstApiException(String message) {
        super("Burst API Error, error description: " + message);
    }
}
