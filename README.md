# burstkit4j

Burstcoin Java Development Framework

[![Build Status](https://travis-ci.com/burst-apps-team/burstkit4j.svg?branch=master)](https://travis-ci.com/burst-apps-team/burstkit4j)
[![](https://jitpack.io/v/burst-apps-team/burstkit4j.svg)](https://jitpack.io/#burst-apps-team/burstkit4j)

## Beta Status

This project is currently in beta. Please report any issues through the GitHub [issue tracker](https://github.com/burst-apps-team/burstkit4j/issues).

## Including in your project

* Maven:

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
<dependencies>
    <dependency>
	    <groupId>com.github.burst-apps-team</groupId>
	    <artifactId>burstkit4j</artifactId>
	    <version>v0.9-beta1</version>
	</dependency>
</dependencies>
```

* Gradle:

```gradle
repositories {
	maven { url 'https://jitpack.io' }
}
dependencies {
	implementation 'com.github.burst-apps-team:burstkit4j:v0.9-beta1'
}
```

## Usage

* Commonly used Burst objects / entities / helper classes can be found in the [entity](burstKit/src/main/java/burst/kit/entity) package.

* For locally-performed cryptographic operations such as encrypting/decrypting, signing/verifying, etc please see the [BurstCrypto](burstKit/src/main/java/burst/kit/burst/BurstCrypto.java) interface (Use `BurstCrypto.getInstance()` to obtain a singleton instance)

* For Burst Node API calls such as making transactions and looking at blocks/accounts/transactions, please see the [BurstNodeService](burstKit/src/main/java/burst/kit/service/BurstNodeService.java) interface. (Use `BurstNodeService.getInstance("http://nodeAddress.com:8125")` to obtain an instance.)

The `BurstNodeService` wraps the returned values in RxJava Singles. You can create your own `SchedulerAssigner` to automatically make all returned values subscribe on the specified schedulers. If you don't want to use RxJava, call `toFuture()` on any Single.

GSON is used for JSON serialization/deserialization. To obtain a `GsonBuilder` customized to serialize/deserialize BurstKit entities, call `BurstKitUtils.buildGson()`.

## Examples

* Sending a transaction containing an encrypted message

```java
import burst.kit.burst.BurstCrypto;
import burst.kit.entity.BurstValue;
import burst.kit.entity.HexStringByteArray;
import burst.kit.entity.response.BRSError;
import burst.kit.entity.response.BroadcastTransactionResponse;
import burst.kit.service.BurstNodeService;
import io.reactivex.disposables.Disposable;

public class TransactionSender {
    /**
     * Example which sends 1 BURST to another account with a fee of 0.1 BURST
     * and includes an encrypted message which only the sender or recipient
     * can read. Performs all cryptographic functions (encrypting the message
     * and signing the transaction) offline so does not send the passphrase
     * to the node (which would be a huge security risk!)
     */
    public void sendTransactionWithEncryptedMessage() {
        // Obtain handles to services
        BurstCrypto burstCrypto = BurstCrypto.getInstance();
        BurstNodeService burstNodeService = BurstNodeService.getInstance("https://wallet.dev.burst-test.net");

        String passphrase = "passphrase"; // Your burst wallet passphrase
        byte[] recipientPublicKey = new HexStringByteArray("AABBCC112233").getBytes(); // Recipient public key

        // Generate the transaction without signing it
        Disposable disposable = burstNodeService.generateTransactionWithEncryptedMessage(burstCrypto.getBurstAddressFromPublic(recipientPublicKey), burstCrypto.getPublicKey(passphrase), BurstValue.fromBurst(1), BurstValue.fromBurst(0.1), 1440, burstCrypto.encryptTextMessage("Sent from burstkit4j!", passphrase, recipientPublicKey))
                .flatMap(response -> {
                    // Now we need to locally sign the transaction.
                    // Get the unsigned transaction bytes from the node's response
                    byte[] unsignedTransactionBytes = response.getUnsignedTransactionBytes().getBytes();
                    // Locally sign the transaction using our passphrase
                    byte[] signedTransactionBytes = burstCrypto.signTransaction(passphrase, unsignedTransactionBytes);
                    // Broadcast the transaction through the node, still not sending it any sensitive information. Use this as the result of the flatMap so we do not have to call subscribe() twice
                    return burstNodeService.broadcastTransaction(signedTransactionBytes);
                })
                .subscribe(this::onTransactionSent, this::handleError);
    }
    
    private void onTransactionSent(BroadcastTransactionResponse response) {
        // Get the transaction ID of the newly sent transaction!
        System.out.println("Transaction sent! Transaction ID: " + response.getTransactionID().getID());
    }
    
    private void handleError(Throwable t) {
        if (t instanceof BRSError) {
            System.out.println("Caught BRS Error: " + ((BRSError) t).getDescription());
        } else {
            t.printStackTrace();
        }
    }
}
``` 
