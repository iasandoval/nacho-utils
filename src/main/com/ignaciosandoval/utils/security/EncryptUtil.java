package com.ignaciosandoval.utils.security;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * Utility class to encrypt and decrypt a text or file using the
 * Advanced Encryption Standard algorithm with the Galois/Counter Mode
 * and No Padding (AES/GCM/NoPadding).
 * <p>
 * The Key is being generated using the Password-based-Key-Derivative-Function
 * with Keyed-Hash Message Authentication Code and Secure Hash Algorithm 1 (PBKDF2WithHmacSHA1).
 *
 * @author iasandoval
 * @version 2.0
 */
public class EncryptUtil {

    /**
     * Galois/Counter Mode Initialization Vector of 16 bits.
     */
    private static final int GCM_IV_LENGTH = 16;

    /**
     * Galois/Counter Mode Tag of 128 bits.
     */
    private static final int GCM_TAG_LENGTH = 128;

    /**
     * Advanced Encryption Standard algorithm.
     */
    private static final String AES = "AES";

    /**
     * Advanced Encryption Standard using the Galois/Counter Mode (GCM) and No
     * Padding.
     */
    private static final String AES_GCM_NOPADDING = "AES/GCM/NoPadding";

    /**
     * Password-based-Key-Derivative-Function with Keyed-Hash Message Authentication
     * Code and Secure Hash Algorithm 1.
     */
    private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";

    /**
     * Enum of Cipher Modes. Encrypt and Decrypt Mode.
     */
    private enum CipherMode {ENCRYPT_MODE, DECRYPT_MODE}

    /**
     * The current Cipher mode. Encrypt or Decrypt Mode.
     */
    private CipherMode cipherMode;

    /**
     * Key used to Encrypt or Decrypt the text.
     */
    private final String key;

    /**
     * Data (Array of bytes) of the Encrypted/Decrypted text or File.
     */
    private byte[] data;

    /**
     * Path with the location of the File to Encrypt or Decrypt.
     */
    private Path path;

    /**
     * Default constructor. It will use the provided KEY.
     *
     * @param key The Key to Encrypt/Decrypt the data.
     */
    public EncryptUtil(String key) {
        this.key = key;
    }

    /**
     * Helper method to write the data in the provided File.
     *
     * @param fileBytes The data to write.
     * @param file      The file to write the data.
     * @throws IOException In case of IOException.
     */
    public static void writeFile(byte[] fileBytes, File file) throws IOException {
        EncryptUtil.writeFile(fileBytes, file.toPath());
    }

    /**
     * Helper method to write the data in the provided Path.
     *
     * @param fileBytes The data to write.
     * @param path      The Path to write the data.
     * @throws IOException In case of IOException.
     */
    public static void writeFile(byte[] fileBytes, Path path) throws IOException {
        try (FileOutputStream outStream = new FileOutputStream(path.toFile())) {
            outStream.write(fileBytes);
        }
    }

    /**
     * Chained method used to Encrypt the provided text.
     *
     * @param textToEncrypt Text to encrypt.
     * @return An EncryptUtil object with the bytes[] set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil encrypt(String textToEncrypt) throws EncryptUtilException {
        this.data = this.encryptText(textToEncrypt);
        return this;
    }

    /**
     * Chained method used to Decrypt the provided text.
     *
     * @param textToDecrypt Text to decrypt.
     * @return An EncryptUtil object with the bytes[] set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil decrypt(String textToDecrypt) throws EncryptUtilException {
        this.data = this.decryptText(textToDecrypt);
        return this;
    }

    /**
     * Chained method used to Encrypt the provided Path object.
     *
     * @param pathToEncrypt The Path with the file location to encrypt.
     * @return An EncryptUtil object with the data (byte[]) and Path object set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil encrypt(Path pathToEncrypt) throws EncryptUtilException {
        this.data = this.encryptPath(pathToEncrypt);
        return this;
    }

    /**
     * Chained method used to Decrypt the provided Path object.
     *
     * @param pathToDecrypt The Path with the file location to decrypt.
     * @return An EncryptUtil object with the data (byte[]) and Path object set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil decrypt(Path pathToDecrypt) throws EncryptUtilException {
        this.data = this.decryptPath(pathToDecrypt);
        return this;
    }

    /**
     * Chained method used to Encrypt the provided File object.
     *
     * @param fileToEncrypt The File object with the location to encrypt.
     * @return An EncryptUtil object with the data (byte[]) and Path object set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil encrypt(File fileToEncrypt) throws EncryptUtilException {
        this.data = this.encryptPath(fileToEncrypt.toPath());
        return this;
    }

    /**
     * Chained method used to Decrypt the provided File object.
     *
     * @param fileToDecrypt The File object with the location to decrypt.
     * @return An EncryptUtil object with the data (byte[]) and Path object set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil decrypt(File fileToDecrypt) throws EncryptUtilException {
        this.data = this.decryptPath(fileToDecrypt.toPath());
        return this;
    }

    /**
     * Chained method used to Encrypt the provided Data (byte[]).
     *
     * @param dataToEncrypt The Data (byte[]) to encrypt.
     * @return An EncryptUtil object with the data (byte[]) set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil encrypt(byte[] dataToEncrypt) throws EncryptUtilException {
        this.data = this.encryptBytes(dataToEncrypt);
        return this;
    }

    /**
     * Chained method used to Decrypt the provided Data (byte[]).
     *
     * @param dataToDecrypt The Data (byte[]) to encrypt.
     * @return An EncryptUtil object with the data (byte[]) set.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    public EncryptUtil decrypt(byte[] dataToDecrypt) throws EncryptUtilException {
        this.data = this.decryptBytes(dataToDecrypt);
        return this;
    }

    /**
     * This method will use the encrypted/decrypted data (bytes[]) to convert it to a String representation.
     * For Encrypted data, will encode the data to Base64.
     * For Decrypted data, will encode the data in UTF-8.
     *
     * @return A String representation of the encrypted data (bytes[]).
     */
    public String toString() {

        String result = "";

        switch (this.cipherMode) {
            case ENCRYPT_MODE:
                result = Base64.getEncoder().encodeToString(this.data);
                break;
            case DECRYPT_MODE:
                result = new String(this.data, StandardCharsets.UTF_8);
                break;
        }

        return result;
    }

    /**
     * This method will return the encrypted/decrypted data.
     *
     * @return The encrypted/decrypted data.
     */
    public byte[] getBytes() {
        return this.data;
    }

    /**
     * This method will write the encrypted/decrypted data (byte[]) into the provided File.
     *
     * @throws IOException In case of IOException.
     */
    public void writeFile() throws IOException {
        EncryptUtil.writeFile(this.data, this.path);
    }

    /**
     * Main method used to encrypt the provided data (bytes[]).
     *
     * @param bytesToEncrypt The data to encrypt.
     * @return The encrypted data.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    private byte[] encryptBytes(byte[] bytesToEncrypt) throws EncryptUtilException {
        try {
            // Generate the Salt
            byte[] salt = this.getSalt();
            // Generate the Secret Key
            SecretKey secretKey = this.getSecretKey(salt);
            // Specifies the set of parameters required by a Cipher using the Galois/Counter Mode (GCM) mode
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, salt);

            // Initializes the cipher
            Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

            // Sets the cipher mode
            this.cipherMode = CipherMode.ENCRYPT_MODE;

            // Perform encryption
            byte[] encryptedBytes = cipher.doFinal(bytesToEncrypt);

            // Concatenate salt + IV + encryptedBytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(salt);
            outputStream.write(gcmParameterSpec.getIV());
            outputStream.write(encryptedBytes);

            return outputStream.toByteArray();

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                IllegalBlockSizeException | IOException ex) {
            throw new EncryptUtilException(ex.getMessage(), ex);
        }
    }

    /**
     * Main method used to decrypt the provided data (bytes[]).
     *
     * @param bytesToDecrypt The data to decrypt.
     * @return The decrypted data.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    private byte[] decryptBytes(final byte[] bytesToDecrypt) throws EncryptUtilException {
        try {
            byte[] salt = Arrays.copyOfRange(bytesToDecrypt, 0, GCM_IV_LENGTH);
            byte[] ivParameter = Arrays.copyOfRange(bytesToDecrypt, GCM_IV_LENGTH, GCM_IV_LENGTH * 2);
            byte[] cipherBytes = Arrays.copyOfRange(bytesToDecrypt, GCM_IV_LENGTH * 2, bytesToDecrypt.length);

            // Generate the Secret Key
            SecretKey keySpec = this.getSecretKey(salt);

            // Specifies the set of parameters required by a Cipher using the Galois/Counter
            // Mode (GCM) mode
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, ivParameter);

            // Initializes the cipher
            Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

            // Sets the cipher mode
            this.cipherMode = CipherMode.DECRYPT_MODE;

            // Perform decryption
            return cipher.doFinal(cipherBytes);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                IllegalBlockSizeException ex) {
            throw new EncryptUtilException(ex.getMessage(), ex);
        }
    }

    /**
     * Method to Encrypt the provided text.
     *
     * @param textToEncrypt Text to Encrypt.
     * @return Encrypted data.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    private byte[] encryptText(String textToEncrypt) throws EncryptUtilException {
        return this.encryptBytes(textToEncrypt.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Method to Decrypt the provided text.
     *
     * @param encryptedText Encrypted text.
     * @return Decrypted data.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    private byte[] decryptText(String encryptedText) throws EncryptUtilException {
        return this.decryptBytes(Base64.getDecoder().decode(encryptedText));
    }

    /**
     * Method to Encrypt the provided Path.
     *
     * @param pathToEncrypt Path to Encrypt.
     * @return Encrypted data.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    private byte[] encryptPath(Path pathToEncrypt) throws EncryptUtilException {
        try {
            this.path = pathToEncrypt;
            return this.encryptBytes(readFile(pathToEncrypt));
        } catch (IOException ex) {
            throw new EncryptUtilException(ex.getMessage(), ex);
        }
    }

    /**
     * Method to Decrypt the provided Path.
     *
     * @param pathToDecrypt Encrypted Path.
     * @return Decrypted data.
     * @throws EncryptUtilException In case of EncryptUtilException.
     */
    private byte[] decryptPath(Path pathToDecrypt) throws EncryptUtilException {
        try {
            this.path = pathToDecrypt;
            return this.decryptBytes(readFile(pathToDecrypt));
        } catch (IOException ex) {
            throw new EncryptUtilException(ex.getMessage(), ex);
        }
    }

    /**
     * Method used to generate the salt data.
     *
     * @return The Salted data.
     */
    private byte[] getSalt() {
        byte[] salt = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(salt);

        return salt;
    }

    /**
     * Method used to generate the SecretKey used to encrypt/decrypt the data.
     *
     * @param salt The Salted data.
     * @return a SecretKey object used to encrypt/decrypt the data.
     * @throws NoSuchAlgorithmException In case of NoSuchAlgorithmException.
     * @throws InvalidKeySpecException  In case of InvalidKeySpecException.
     */
    private SecretKey getSecretKey(byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(this.key.toCharArray(), salt, 65536, 128); // AES-128
        SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA1);
        byte[] key = f.generateSecret(spec).getEncoded();

        return new SecretKeySpec(key, AES);
    }

    /**
     * Method used to read the provided File data.
     *
     * @param path The Path object to read.
     * @return An array of bytes with the File data.
     * @throws IOException In case of IOException.
     */
    private byte[] readFile(Path path) throws IOException {
        return Files.readAllBytes(path);
    }
}
