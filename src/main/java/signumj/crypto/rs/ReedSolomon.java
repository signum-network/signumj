package signumj.crypto.rs;

public interface ReedSolomon {
    String encode(long numeric);
    long decode(String encoded) throws DecodeException;

    @SuppressWarnings("serial")
	class DecodeException extends Exception {
    }
}
