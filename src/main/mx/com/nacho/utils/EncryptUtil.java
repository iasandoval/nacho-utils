package mx.com.nacho.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to encrypt and decrypt a text.
 *
 * @author iasandoval
 * @version 1.0
 */
public class EncryptUtil {

    private static final Logger LOGGER = Logger.getLogger(EncryptUtil.class.getName());
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";
    private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";
    private static final String TEMP_KEY = "P3pePeC4sp1c4Pps";

    private String key;

    public EncryptUtil() {
        this.key = TEMP_KEY;
    }

    public EncryptUtil(String key) {
        this.key = key;
    }

    public String getKey()  {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public EncryptUtil withKey(String key) {
        this.key = key;
        return this;
    }

    public String encrypt(String value) {
        String encrypt = null;
        try {
            byte[] salt = getSalt();
            SecretKey keySpec = this.getSecretKey(salt);
            IvParameterSpec iv = this.getIvParameterSpec();

            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));

            // Concatenate salt + iv + encrypted
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(salt);
            outputStream.write(iv.getIV());
            outputStream.write(encrypted);

            encrypt = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return encrypt;
    }

    public String decrypt(String encrypted) {
        String decrypt = null;
        try {

            byte[] cipherText = Base64.getDecoder().decode(encrypted);
            byte[] salt = Arrays.copyOfRange(cipherText, 0, 16);
            byte[] iv = Arrays.copyOfRange(cipherText, 16, 32);
            byte[] ct = Arrays.copyOfRange(cipherText, 32, cipherText.length);

            SecretKey keySpec = getSecretKey(salt);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));

            byte[] original = cipher.doFinal(ct);

            decrypt = new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return decrypt;
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

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
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);

        return new IvParameterSpec(ivBytes);
    }

}
