[![](https://jitpack.io/v/burst-apps-team/burstkit4j.svg)](https://jitpack.io/#signum-network/signumj)
# SignumJ

Signum Java Development Framework

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
	    <groupId>com.github.signum-network</groupId>
	    <artifactId>signumj</artifactId>
	    <version>v1.0.0</version>
	</dependency>
</dependencies>
```

* Gradle:

```gradle
repositories {
	maven { url 'https://jitpack.io' }
}
dependencies {
	implementation 'com.github.signum-network:signumj:v1.0.0'
}
```

## Usage

* Commonly used Signum objects / entities / helper classes can be found in the [entity](src/main/java/signumj/entity) package.

* For locally-performed cryptographic operations such as encrypting/decrypting, signing/verifying, etc please see the [SignumCrypto](src/main/java/signumj/crypto/SignumCrypto.java) interface (Use `SignumCrypto.getInstance()` to obtain a singleton instance)

* For Singum Node API calls such as making transactions and looking at blocks/accounts/transactions, please see the [NodeService](src/main/java/signumj/service/NodeService.java) interface. (Use `NodeService.getInstance("http://nodeAddress.com:8125")` to obtain an instance.)

The `NodeService` wraps the returned values in RxJava Singles. You can create your own `SchedulerAssigner` to automatically make all returned values subscribe on the specified schedulers. If you don't want to use RxJava, call `toFuture()` on any Single.

GSON is used for JSON serialization/deserialization. To obtain a `GsonBuilder` customized to serialize/deserialize the entities, call `SignumUtils.buildGson()`.

## Examples

Please see the [examples folder](src/test/java/signumj/examples).
Bellow a simple example on how to send a transaction and sign it locally.

```java
import signumj.Constants;
import signumj.crypto.SignumCrypto;
import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.service.NodeService;

/**
 * Example which sends 1 SIGNA to another account with a fee of 0.1 SIGNA.
 * 
 * Performs the cryptographic signature locally so does not send the passphrase
 * to over the wire.
 * 
 */
public class SendTransactionBlockingGet {

	public static void main(String[] args) {
        NodeService node = NodeService.getInstance(Constants.HTTP_NODE_EUROPE2);

        String passphrase = "YOUR SENDING ACCOUNT PASSPHRASE, USED TO SIGN MESSAGES LOCALLY";
        
        SignumAddress recipient = SignumAddress.fromRs("S-JJQS-MMA4-GHB4-4ZNZU");
        SignumValue amountToSend = SignumValue.fromSigna(1);
        SignumValue fee = SignumValue.fromSigna(0.1);
        int deadline = 1440; // deadline in minutes before this transaction becomes invalid

        // Generate the transaction without signing it
        byte[] unsignedTransactionBytes = node.generateTransaction(recipient,
        		SignumCrypto.getInstance().getPublicKey(passphrase), amountToSend, fee, deadline, null).blockingGet();
        
        // Locally sign the transaction using our passphrase
        byte[] signedTransactionBytes = SignumCrypto.getInstance().signTransaction(passphrase, unsignedTransactionBytes);
        
        // Broadcast the transaction through the node, still not sending it any sensitive information.
        node.broadcastTransaction(signedTransactionBytes).blockingGet();
    }
}
``` 
