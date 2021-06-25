package burst.kit.crypto.rs;

public interface ReedSolomon {
    String encode(long numeric);
    long decode(String encoded) throws DecodeException;

    class DecodeException extends Exception {
    }
}
