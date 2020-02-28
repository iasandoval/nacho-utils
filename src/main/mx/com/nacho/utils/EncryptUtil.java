package mx.com.nacho.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * Utility class to encrypt and decrypt a text.
 *
 * @author iasandoval
 * @version 1.0
 */
public class EncryptUtil {

    private static final int BYTE_SIZE_16 = 16;

    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";

    private static final String DEFAULT_KEY = "P3pePeC4sp1c4Pps";
    private static final String DEFAULT_TEXT = "nacho-utils";

    /**
     * Key used to Encrypt or Decrypt the text.
     */
    private String key;

    /**
     * Text to Encrypt or Decrypt.
     */
    private String text;

    /**
     * Default constructor. It will use the Default KEY and Default TEXT.
     */
    public EncryptUtil() {
        this.key = DEFAULT_KEY;
        this.text = DEFAULT_TEXT;
    }

    /**
     * Constructor by text.
     *
     * @param text This is the text to Encrypt or Decrypt.
     */
    public EncryptUtil(String text) {
        this.key = DEFAULT_KEY;
        this.text = text;
    }

    /**
     * Get the key used to Encrypt or Decrypt.
     *
     * @return The key used to Encrypt or Decrypt.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Set the key to use for Encrypt or Decrypt.
     *
     * @param key The key to use for Encrypt or Decrypt.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Chained method to set the key to use for Encrypt or Decrypt.
     *
     * @param key The key to use for Encrypt or Decrypt.
     * @return The instance of EncryptUtil.
     */
    public EncryptUtil withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Chained method to Encrypt the provided text.
     *
     * @return Encrypted String.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public String encrypt() throws EncryptUtilException {
        return this.encrypt(this.text);
    }

    /**
     * Chained method to Decrypt the provided text.
     *
     * @return Decryted String.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public String decrypt() throws EncryptUtilException {
        return this.decrypt(this.text);
    }

    /**
     * Method to Encrypt the provided text.
     *
     * @param textToEncrypt Text to Encrypt.
     * @return Encrypted String.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public String encrypt(String textToEncrypt) throws EncryptUtilException {
        try {
            byte[] salt = this.getSalt();
            SecretKey secretKey = this.getSecretKey(salt);
            IvParameterSpec ivParameter = this.getIvParameterSpec();

            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameter);

            byte[] encrypted = cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));

            // Concatenate salt + iv + encrypted
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(salt);
            outputStream.write(ivParameter.getIV());
            outputStream.write(encrypted);

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                IllegalBlockSizeException | IOException ex) {
            throw new EncryptUtilException(ex.getMessage(), ex);
        }
    }

    /**
     * Method to Decrypt the provided text.
     *
     * @param encryptedText Encrypted text.
     * @return Decrypted String.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public String decrypt(String encryptedText) throws EncryptUtilException {
        try {
            byte[] cipherText = Base64.getDecoder().decode(encryptedText);
            byte[] salt = Arrays.copyOfRange(cipherText, 0, BYTE_SIZE_16);
            byte[] ivParameter = Arrays.copyOfRange(cipherText, BYTE_SIZE_16, BYTE_SIZE_16 * 2);
            byte[] cipherTextBytes = Arrays.copyOfRange(cipherText, BYTE_SIZE_16 * 2, cipherText.length);

            SecretKey keySpec = this.getSecretKey(salt);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivParameter));

            byte[] original = cipher.doFinal(cipherTextBytes);

            return new String(original, StandardCharsets.UTF_8);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                IllegalBlockSizeException ex) {
            throw new EncryptUtilException(ex.getMessage(), ex);
        }

    }

    private byte[] getSalt() {
        byte[] salt = new byte[BYTE_SIZE_16];
        new SecureRandom().nextBytes(salt);

        return salt;
    }

    private SecretKey getSecretKey(byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(this.getKey().toCharArray(), salt, 65536, 128); // AES-128
        SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA1);
        byte[] key = f.generateSecret(spec).getEncoded();

        return new SecretKeySpec(key, AES);
    }

    private IvParameterSpec getIvParameterSpec() {
        SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[BYTE_SIZE_16];
        random.nextBytes(ivBytes);

        return new IvParameterSpec(ivBytes);
    }

    /**
     * Wrapper Exception.
     */
    public class EncryptUtilException extends Exception {

        public EncryptUtilException() {
            super();
        }

        public EncryptUtilException(String message) {
            super(message);
        }

        public EncryptUtilException(String message, Throwable cause) {
            super(message, cause);
        }

        public EncryptUtilException(Throwable cause) {
            super(cause);
        }

        protected EncryptUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
