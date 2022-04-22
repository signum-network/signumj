package signumj.entity.response.http;

public final class IndirectIncomingResponse extends BRSResponse {
    private final String amountNQT;
    private final String quantityQNT;
    private final int confirmations;
    private final int height;

    public IndirectIncomingResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String amountNQT, String quantityQNT, int confirmations, int height) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.amountNQT = amountNQT;
        this.quantityQNT = quantityQNT;
        this.confirmations = confirmations;
        this.height = height;
    }

    public String getAmountNQT() {
        return amountNQT;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public int getHeight() {
        return height;
    }
}
