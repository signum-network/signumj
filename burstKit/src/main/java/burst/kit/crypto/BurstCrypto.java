package burst.kit.crypto;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public interface BurstCrypto {

    /**
     * @return A new SHA-256 MessageDigest
     */
    MessageDigest getSha256();

    /**
     * @return A new Shabal-256 MessageDigest
     */
    MessageDigest getShabal256();

    /**
     * @return A new RIPEMD-160 MessageDigest
     */
    MessageDigest getRipeMD160();

    /**
     * Derive the private key of the passphrase
     * @param passphrase The passphrase
     * @return The private key
     */
    byte[] getPrivateKey(String passphrase);

    /**
     * Derive the public key of the passphrase
     * @param passphrase The passphrase
     * @return The public key
     */
    byte[] getPublicKey(String passphrase);

    /**
     * Derive the public key of the private key
     * @param privateKey The private key
     * @return The public key
     */
    byte[] getPublicKey(byte[] privateKey);

    /**
     * Derive the burst address of the passphrase
     * @param passphrase The passphrase
     * @return The burst address
     */
    BurstAddress getBurstAddressFromPassphrase(String passphrase);

    /**
     * Derive the burst address of the private key
     * @param privateKey The private key
     * @return The burst address
     */
    BurstAddress getBurstAddressFromPrivate(byte[] privateKey);

    /**
     * Derive the burst address of the public key
     * @param publicKey The public key
     * @return The burst address
     */
    BurstAddress getBurstAddressFromPublic(byte[] publicKey);

    /**
     * Convert a full hash to an ID
     * @param hash The hash of an account, block, transaction etc
     * @return The Burst ID of the account, block, transaction etc
     * @throws IllegalArgumentException if the hash is invalid
     */
    BurstID hashToId(byte[] hash) throws IllegalArgumentException;

    /**
     * Derive the shared secret between two public/private key combinations
     * @param myPassphrase Your passphrase
     * @param theirPublicKey Their public key
     * @return The shared secret between you and them
     */
    byte[] getSharedSecret(String myPassphrase, byte[] theirPublicKey);

    /**
     * Derive the shared secret between two public/private key combinations
     * @param myPrivateKey Your private key
     * @param theirPublicKey Their public key
     * @return The shared secret between you and them
     */
    byte[] getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Generate the signature of a message
     * @param message The message to sign
     * @param passphrase The passphrase to sign with
     * @return The signature
     */
    byte[] sign(byte[] message, String passphrase);

    /**
     * Generate the signature of a message
     * @param message The message to sign
     * @param privateKey The private key to sign with
     * @return The signature
     */
    byte[] sign(byte[] message, byte[] privateKey);

    /**
     * Generate the signature of a message
     * @param message The message to sign
     * @param passphrase The private key to sign with
     * @return The signature
     */
    byte[] sign(String message, String passphrase);

    /**
     * Generate the signature of a message
     * @param message The message to sign
     * @param privateKey The private key to sign with
     * @return The signature
     */
    byte[] sign(String message, byte[] privateKey);

    /**
     * Sign an unsigned transaction
     * @param passphrase Passphrase to sign with
     * @param unsignedTransaction The unsigned transaction bytes
     * @return The signed transaction bytes
     */
    byte[] signTransaction(String passphrase, byte[] unsignedTransaction);

    /**
     * Sign an unsigned transaction
     * @param privateKey Private key to sign with
     * @param unsignedTransaction The unsigned transaction bytes
     * @return The signed transaction bytes
     */
    byte[] signTransaction(byte[] privateKey, byte[] unsignedTransaction);

    /**
     * Verify is a signature is valid
     * @param signature The signature
     * @param message The message that was signed
     * @param publicKey The public key of the signee
     * @param enforceCanonical Whether to insist that the signature is canonical
     * @return Whether the signature is valid or not
     */
    boolean verify(byte[] signature, byte[] message, byte[] publicKey, boolean enforceCanonical);

    /**
     * Encrypt a message using AES
     * @param plaintext The message to encrypt
     * @param signingKey The key to encrypt with (Must be 32 bytes long!)
     * @return The encrypted message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesEncrypt(byte[] plaintext, byte[] signingKey) throws IllegalArgumentException;

    /**
     * Encrypt a message using AES
     * @param plaintext The message to encrypt
     * @param signingKey The key to encrypt with (Must be 32 bytes long!)
     * @param nonce The nonce to use for the encryption
     * @return The encrypted message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesEncrypt(byte[] plaintext, byte[] signingKey, byte[] nonce) throws IllegalArgumentException;

    /**
     * Encrypt a message using AES
     * @param plaintext The message to encrypt
     * @param signingKey The key to encrypt with (Must be 32 bytes long!)
     * @return The encrypted message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesEncrypt(String plaintext, byte[] signingKey) throws IllegalArgumentException;

    /**
     * Encrypt a message using AES
     * @param plaintext The message to encrypt
     * @param signingKey The key to encrypt with (Must be 32 bytes long!)
     * @param nonce The nonce to use for the encryption
     * @return The encrypted message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesEncrypt(String plaintext, byte[] signingKey, byte[] nonce) throws IllegalArgumentException;

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce to use for the encryption
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPrivateKey Your private keyt (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPrivateKey Your private keyt (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce to use for the encryption
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce to use for the encryption
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPrivateKey Your private keyt (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param plaintext The message to encrypt
     * @param myPrivateKey Your private keyt (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce to use for the encryption
     * @return The encrypted message
     */
    byte[] aesSharedEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);

    /**
     * Decrypt an AES-encrypted message
     * @param encrypted The encrypted message
     * @param signingKey The key used for encryption (Must be 32 bytes long!)
     * @return The plaintext message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesDecrypt(byte[] encrypted, byte[] signingKey) throws IllegalArgumentException;

    /**
     * Decrypt an AES-encrypted message
     * @param encrypted The encrypted message
     * @param signingKey The key used for encryption (Must be 32 bytes long!)
     * @param nonce The nonce used for the encryption
     * @return The plaintext message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesDecrypt(byte[] encrypted, byte[] signingKey, byte[] nonce) throws IllegalArgumentException;

    /**
     * Decrypt an AES-encrypted message
     * @param encrypted The encrypted message
     * @param signingKey The key used for encryption (Must be 32 bytes long!)
     * @return The plaintext message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesDecrypt(String encrypted, byte[] signingKey) throws IllegalArgumentException;

    /**
     * Decrypt an AES-encrypted message
     * @param encrypted The encrypted message
     * @param signingKey The key used for encryption (Must be 32 bytes long!)
     * @param nonce The nonce used for the encryption
     * @return The plaintext message
     * @throws IllegalArgumentException if the key is not 32 bytes long
     */
    byte[] aesDecrypt(String encrypted, byte[] signingKey, byte[] nonce) throws IllegalArgumentException;

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce used for the encryption
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce used for the encryption
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(String encrypted, String myPassphrase, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce used for the encryption
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(String encrypted, String myPassphrase, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(String encrypted, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Encrypt a message using AES using a shared secret
     * @param encrypted The encrypted message
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @param nonce The nonce used for the encryption
     * @return The plaintext message
     */
    byte[] aesSharedDecrypt(String encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);

    /**
     * Encrypt the bytes as a non-text encrypted message (to be included in a transaction)
     * @param message The message to encrypt
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    BurstEncryptedMessage encryptBytesMessage(byte[] message, String myPassphrase, byte[] theirPublicKey);

    /**
     * Encrypt the bytes as a non-text encrypted message (to be included in a transaction)
     * @param message The message to encrypt
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    BurstEncryptedMessage encryptBytesMessage(byte[] message, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Encrypt the text as a text encrypted message (to be included in a transaction)
     * @param message The message to encrypt
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    BurstEncryptedMessage encryptTextMessage(String message, String myPassphrase, byte[] theirPublicKey);

    /**
     * Encrypt the text as a text encrypted message (to be included in a transaction)
     * @param message The message to encrypt
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The encrypted message
     */
    BurstEncryptedMessage encryptTextMessage(String message, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Decrypt an encrypted message (such as one included in a transaction)
     * @param message The encrypted message
     * @param myPassphrase Your passphrase (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The plaintext message
     */
    byte[] decryptMessage(BurstEncryptedMessage message, String myPassphrase, byte[] theirPublicKey);

    /**
     * Decrypt an encrypted message (such as one included in a transaction)
     * @param message The encrypted message
     * @param myPrivateKey Your private key (for deriving shared secret)
     * @param theirPublicKey Their public key (for deriving shared secret)
     * @return The plaintext message
     */
    byte[] decryptMessage(BurstEncryptedMessage message, byte[] myPrivateKey, byte[] theirPublicKey);

    /**
     * Get the Reed-Solomon encoding of a Burst address. Does not include the "BURST-" prefix.
     * @param burstID The Burst ID
     * @return The Reed-Solomon encoding of the address, not including the "BURST-" prefix.
     */
    String rsEncode(BurstID burstID);

    /**
     * Get the account ID associated with a Reed-Solomon encoded address
     * @param rs The Reed-Solomon encoded address, not including the "BURST-" prefix
     * @return The ID of that RS address
     * @throws IllegalArgumentException If the RS address was invalid and could not be decoded.
     */
    BurstID rsDecode(String rs) throws IllegalArgumentException;

    /**
     * Translate an epoch time to a Java Date object
     * @param epochTime The number of seconds since the Burst epoch
     * @return The timestamp
     */
    Date fromEpochTime(long epochTime);

    /**
     * Converts up to the first 8 bytes of a byte array to a long.
     * @param bytes The byte array
     * @return The long representation of the first 8 bytes
     */
    long bytesToLong(byte[] bytes);

    /**
     * Converts up to the first 4 bytes of a byte array to a long.
     * @param bytes The byte array
     * @return The int representation of the first 4 bytes
     */
    int bytesToInt(byte[] bytes);

    /**
     * Converts up to the first 8 bytes of a byte array to a long.
     * @param l The long to be converted
     * @return The long represented as an 8 long byte array.
     */
    byte[] longToBytes(long l);

    /**
     * Converts up to the first 4 bytes of a byte array to a long.
     * @param i The integer to be converted
     * @return The int represented as a 4 long byte array.
     */
    byte[] intToBytes(int i);

    /**
     * Hex-encode a byte array
     * @param bytes Data to be encoded
     * @return Hexadecimal encoded form of bytes
     */
    String toHexString(byte[] bytes);

    /**
     * Decode a hex-encoded byte array
     * @param string The hexadecimal encoded form of the bytes
     * @return The decoded bytes
     */
    byte[] parseHexString(String string);

    /**
     * Calculate the generation signature of a block
     * @param lastGenSig The generation signature of the previous block
     * @param lastGenerator The ID of the last block's generator
     * @return The generation signature of the block
     */
    byte[] calculateGenerationSignature(byte[] lastGenSig, long lastGenerator);

    /**
     * Calculate the generation signature of a block
     * @param lastGenSig The generation signature of the previous block
     * @param lastGenerator The ID of the last block's generator
     * @return The generation signature of the block
     */
    byte[] calculateGenerationSignature(byte[] lastGenSig, BurstAddress lastGenerator);

    /**
     * Calculate which scoop a block will use
     * @param genSig The generation signature of the block
     * @param height The height of the block
     * @return The scoop for the block
     */
    int calculateScoop(byte[] genSig, long height);

    /**
     * Calculate the hit (raw value obtained from a scoop)
     * @param accountId The account ID
     * @param nonce The nonce
     * @param genSig The generation signature
     * @param scoop The scoop
     * @param pocVersion The PoC version. If unsure, use 2.
     * @return The hit of that scoop
     */
    BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion);

    /**
     * Calculate the hit (raw value obtained from a scoop)
     * @param accountId The account ID
     * @param nonce The nonce
     * @param genSig The generation signature
     * @param scoop The scoop
     * @param pocVersion The PoC version. If unsure, use 2.
     * @return The hit of that scoop
     */
    BigInteger calculateHit(BurstAddress accountId, long nonce, byte[] genSig, int scoop, int pocVersion);

    /**
     * Calculate the hit (raw value obtained from a scoop)
     * @param accountId The account ID
     * @param nonce The nonce
     * @param genSig The generation signature
     * @param scoopData The scoop data, usually read from a disk.
     * @return The hit of that scoop
     */
    BigInteger calculateHit(long accountId, long nonce, byte[] genSig, byte[] scoopData);

    /**
     * Calculate the hit (raw value obtained from a scoop)
     * @param accountId The account ID
     * @param nonce The nonce
     * @param genSig The generation signature
     * @param scoopData The scoop data, usually read from a disk.
     * @return The hit of that scoop
     */
    BigInteger calculateHit(BurstAddress accountId, long nonce, byte[] genSig, byte[] scoopData);

    /**
     * Calculate the deadline (hit / baseTarget)
     * @param accountId The account ID
     * @param nonce The nonce
     * @param genSig The generation signature
     * @param scoop The scoop
     * @param baseTarget The base target
     * @param pocVersion The PoC version. If unsure, use 2.
     * @return The hit of that scoop
     */
    BigInteger calculateDeadline(long accountId, long nonce, byte[] genSig, int scoop, long baseTarget, int pocVersion);

    /**
     * Calculate the deadline (hit / baseTarget)
     * @param accountId The account ID
     * @param nonce The nonce
     * @param genSig The generation signature
     * @param scoop The scoop
     * @param baseTarget The base target
     * @param pocVersion The PoC version. If unsure, use 2.
     * @return The hit of that scoop
     */
    BigInteger calculateDeadline(BurstAddress accountId, long nonce, byte[] genSig, int scoop, long baseTarget, int pocVersion);

    /**
     * TODO javadoc
     * @param atVersion
     * @param code
     * @param data
     * @param dPages
     * @param csPages
     * @param usPages
     * @param minActivationAmount
     * @return
     */
    byte[] getATCreationBytes(short atVersion, byte[] code, byte[] data, int dPages, int csPages, int usPages, BurstValue minActivationAmount);

    /**
     * Get a singleton instance of this class
     * @return Singleton instance of this class.
     */
    static BurstCrypto getInstance() {
        return BurstCryptoImpl.INSTANCE;
    }
}
