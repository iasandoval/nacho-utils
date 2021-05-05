package mx.com.nacho.utils.test;

import mx.com.nacho.utils.EncryptUtil;
import mx.com.nacho.utils.EncryptUtilException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;

public class EncryptUtilTest {

    @Test
    public void testText() {
        try {
            // Encrypt text "nacho" using key "secret"
            String encrypted = new EncryptUtil("secret").encrypt("nacho").toString();

            // Decrypt encrypted text using key "secret"
            String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();

            System.out.println("Encrypted Text: " + encrypted);
            System.out.println("Decrypted Text: " + decrypted);
        } catch (EncryptUtilException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFile() {
        try {
            // Keep original file
            File file = new File ("files/chicken.pdf");

            // Copy file to Encrypt
            File fileEncrypted = new File ("files/chicken_encrypted.pdf");
            Files.copy(file.toPath(), fileEncrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Encrypt File
            byte[] encryptedBytes = new EncryptUtil("secret").encrypt(fileEncrypted).getBytes();

            // Write Encrypted File
            EncryptUtil.writeFile(encryptedBytes, fileEncrypted);

            // Decrypt File
            byte[] decryptedBytes = new EncryptUtil("secret").decrypt(fileEncrypted).getBytes();

            // Write Decrypted File
            File fileDecrypted = new File ("files/chicken_decrypted.pdf");
            EncryptUtil.writeFile(decryptedBytes, fileDecrypted);

        } catch (EncryptUtilException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteFile() {
        try {
            // Keep original file
            File file = new File ("files/chicken.pdf");

            // Copy file to Encrypt, adding "_encrypted" to the file name.
            File fileEncrypted = new File ("files/chicken_encrypted.pdf");
            Files.copy(file.toPath(), fileEncrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Encrypt and Write File. This will create file "files/chicken_encrypted.pdf".
            new EncryptUtil("secret").encrypt(fileEncrypted).writeFile();

            // Copy File to Decrypt, adding "_decrypted" to the file name.
            File fileDecrypted = new File ("files/chicken_decrypted.pdf");
            Files.copy(fileEncrypted.toPath(), fileDecrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Decrypt File and Write File. This will create file "files/chicken_decrypted.pdf".
            new EncryptUtil("secret").decrypt(fileDecrypted).writeFile();

        } catch (EncryptUtilException | IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testToBase64File() {
        try {
            // Text file to encrypt
            File file = new File ("files/file.txt");

            // Encrypt file and return its Base64 string
            String encrypted = new EncryptUtil("secret").encrypt(file).toString();

            // Decrypt Base64 File
            String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();

            System.out.println("Encrypted File: " + encrypted);
            System.out.println("Decrypted File: " + decrypted);

        } catch (EncryptUtilException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetData() {
        try {
            // Encrypt text "nacho" using key "secret"
            byte[] encrypted = new EncryptUtil("secret").encrypt("nacho").getBytes();

            // Decrypt encrypted text using key "secret"
            byte[] decrypted = new EncryptUtil("secret").decrypt(encrypted).getBytes();

            System.out.println("Encrypted Data: " + encrypted);
            System.out.println("Decrypted Data: " + decrypted);
        } catch (EncryptUtilException e) {
            e.printStackTrace();
        }
    }
}
