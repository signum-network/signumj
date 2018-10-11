package burst.kit.entity.response;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class AccountPublicKeyResponse extends BRSResponse {
    private HexStringByteArray publicKey;

    private AccountPublicKeyResponse() {}

    public HexStringByteArray getPublicKey() {
        return publicKey;
    }
}
