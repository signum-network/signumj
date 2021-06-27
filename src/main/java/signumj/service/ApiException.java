package signumj.service;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super("Signum API Error, error description: " + message);
    }
}
