package signumj.entity.response.http;

public final class AliasesResponse extends BRSResponse {
    private final AliasResponse[] aliases;

    public AliasesResponse(AliasResponse[] aliases) {
        this.aliases = aliases;
    }

    public AliasResponse[] getAliases() {
        return aliases;
    }
}
