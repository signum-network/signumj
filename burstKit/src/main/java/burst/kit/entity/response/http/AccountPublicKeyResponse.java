package burst.kit.entity.response.http;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public final class AccountPublicKeyResponse extends BRSResponse {
    private final HexStringByteArray publicKey;

    public AccountPublicKeyResponse(HexStringByteArray publicKey) {
        this.publicKey = publicKey;
    }

    public HexStringByteArray getPublicKey() {
        return publicKey;
    }
}
