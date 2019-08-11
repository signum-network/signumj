package burst.kit.service;

public class BurstApiException extends Exception {
    public BurstApiException(String message) {
        super("Burst API Error, error description: " + message);
    }
}
