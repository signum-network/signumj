# burstkit4j

Burstcoin Java Development Framework

[![Build Status](https://travis-ci.com/harry1453/burstkit4j.svg?token=YRshfVv1szv21KJ3KRSM&branch=master)](https://travis-ci.com/harry1453/burstkit4j)

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
import burst.kit.entity.response.BroadcastTransactionResponse;
import burst.kit.entity.response.GenerateTransactionResponse;
import burst.kit.service.BurstNodeService;

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

        // Generate the transaction we want to, without sending any sensitive information to the node
        // WARNING: Do not use blockingGet() in a non-test environment as it blocks the current thread until a response is received or the request times out.
        GenerateTransactionResponse generatedTransaction = burstNodeService.generateTransactionWithEncryptedMessage(burstCrypto.getBurstAddressFromPublic(recipientPublicKey), burstCrypto.getPublicKey(passphrase), BurstValue.fromBurst(1), BurstValue.fromBurst(0.1), 1440, burstCrypto.encryptTextMessage("Sent from burstkit4j!", passphrase, recipientPublicKey)).blockingGet();
        
        // Check no errors occurred in generating the transaction
        if (generatedTransaction.hasError()) {
            System.err.println("Error generating transaction: " + generatedTransaction.getErrorDescription());
            return;
        }
        
        // Get the unsigned transaction bytes from the node's response
        byte[] unsignedTransactionBytes = generatedTransaction.getUnsignedTransactionBytes().getBytes();
        
        // Locally sign the transaction using our passphrase
        byte[] signedTransactionBytes = burstCrypto.signTransaction(passphrase, unsignedTransactionBytes);

        // Broadcast the transaction through the node, still not sending it any sensitive information
        // WARNING: Do not use blockingGet() in a non-test environment as it blocks the current thread until a response is received or the request times out.
        BroadcastTransactionResponse broadcastTransactionResponse = burstNodeService.broadcastTransaction(signedTransactionBytes).blockingGet();
        
        // Check no errors occurred in broadcasting the transaction
        if (broadcastTransactionResponse.hasError()) {
            System.err.println("Error broadcasting transaction: " + broadcastTransactionResponse.getErrorDescription());
            return;
        }
        
        // Get the transaction ID of the newly sent transaction!
        System.out.println("Transaction sent! Transaction ID: " + broadcastTransactionResponse.getTransactionID().getID());
    }
}
``` 
