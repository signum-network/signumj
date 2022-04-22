package signumj.entity.response;

import signumj.entity.SignumValue;
import signumj.entity.response.http.IndirectIncomingResponse;

public class IndirectIncoming {
    private final SignumValue amount;
    private final SignumValue quantity;
    private final int blockHeight;
    private final int confirmations;

    public IndirectIncoming(SignumValue amount, SignumValue quantity, int blockHeight, int confirmations) {
        this.amount = amount;
        this.quantity = quantity;
        this.blockHeight = blockHeight;
        this.confirmations = confirmations;
    }

    public IndirectIncoming(IndirectIncomingResponse indirectResponse) {
        this.amount = SignumValue.fromNQT(indirectResponse.getAmountNQT());
        this.quantity = SignumValue.fromNQT(indirectResponse.getQuantityQNT());
        this.blockHeight = indirectResponse.getHeight();
        this.confirmations = indirectResponse.getConfirmations();
    }

    public SignumValue getAmount() {
        return amount;
    }

    public SignumValue getQuantity() {
        return quantity;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getConfirmations() {
        return confirmations;
    }
}
