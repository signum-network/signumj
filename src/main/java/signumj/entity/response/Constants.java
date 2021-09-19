package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.http.ConstantsResponse;
import signumj.entity.response.http.TransactionSubtypeResponse;
import signumj.entity.response.http.TransactionTypeResponse;

import java.util.Arrays;

public class Constants {
    private final int maxBlockPayloadLength;
    private final int maxArbitraryMessageLength;
    private final SignumID genesisBlockId;
    private final SignumAddress genesisAccountId;
    private final TransactionType[] transactionTypes;
    private final String addressPrefix;
	private final String valueSuffix;

    public Constants(ConstantsResponse constantsResponse) {
        this.maxBlockPayloadLength = constantsResponse.getMaxBlockPayloadLength();
        this.maxArbitraryMessageLength = constantsResponse.getMaxArbitraryMessageLength();
        this.genesisBlockId = SignumID.fromLong(constantsResponse.getGenesisBlockId());
        this.genesisAccountId = SignumAddress.fromEither(constantsResponse.getGenesisAccountId());
        this.addressPrefix = constantsResponse.getAddressPrefix();
        this.valueSuffix = constantsResponse.getValueSuffix();
        this.transactionTypes = Arrays.stream(constantsResponse.getTransactionTypes())
                .map(TransactionType::new)
                .toArray(TransactionType[]::new);
    }

    public int getMaxBlockPayloadLength() {
        return maxBlockPayloadLength;
    }

    public int getMaxArbitraryMessageLength() {
        return maxArbitraryMessageLength;
    }

    public SignumAddress getGenesisAccountId() {
        return genesisAccountId;
    }

    public SignumID getGenesisBlockId() {
        return genesisBlockId;
    }
    
    public String getAddressPrefix() {
		return addressPrefix;
	}

	public String getValueSuffix() {
		return valueSuffix;
	}

    public TransactionType[] getTransactionTypes() {
        return transactionTypes;
    }

    public static class TransactionType {
        private final String description;
        private final int type;
        private final Subtype[] subtypes;

        public TransactionType(String description, int type, Subtype[] subtypes) {
            this.description = description;
            this.type = type;
            this.subtypes = subtypes;
        }

        public TransactionType(TransactionTypeResponse transactionTypeResponse) {
            this.description = transactionTypeResponse.getDescription();
            this.type = transactionTypeResponse.getValue();
            this.subtypes = Arrays.stream(transactionTypeResponse.getSubtypes())
                    .map(Subtype::new)
                    .toArray(Subtype[]::new);
        }

        public String getDescription() {
            return description;
        }

        public int getType() {
            return type;
        }

        public Subtype[] getSubtypes() {
            return subtypes;
        }

        public static class Subtype {
            private final String description;
            private final int subtype;
            private final SignumValue minimumFeeConstant;
            private final SignumValue minimumFeeAppendages;

            public Subtype(TransactionSubtypeResponse transactionSubtypeResponse) {
                this.description = transactionSubtypeResponse.getDescription();
                this.subtype = transactionSubtypeResponse.getValue();
                this.minimumFeeConstant = SignumValue.fromNQT(transactionSubtypeResponse.getMinimumFeeConstantNQT());
                this.minimumFeeAppendages = SignumValue.fromNQT(transactionSubtypeResponse.getMinimumFeeAppendagesNQT());
            }

            public String getDescription() {
                return description;
            }

            public int getSubtype() {
                return subtype;
            }
            
            public SignumValue getMinimumFeeConstant() {
                return minimumFeeConstant;
            }
            
            public SignumValue getMinimumFeeAppendages() {
                return minimumFeeAppendages;
            }
        }
    }
}
