package mx.com.nacho.utils.test;

import mx.com.nacho.utils.EncryptUtil;
import org.junit.Test;

public class EncryptUtilTest {

    @Test
    public void testTraditional() {
        try {
            EncryptUtil enc = new EncryptUtil();
            enc.setKey("secret");

            String encrypted = enc.encrypt("nacho");
            String decrypted = enc.decrypt(encrypted);

            System.out.println(encrypted);
            System.out.println(decrypted);
        } catch (EncryptUtil.EncryptUtilException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChained() {
        try {
            String encrypted = new EncryptUtil("nacho").withKey("secret").encrypt();
            String decrypted = new EncryptUtil(encrypted).withKey("secret").decrypt();

            System.out.println(encrypted);
            System.out.println(decrypted);
        } catch (EncryptUtil.EncryptUtilException e) {
            e.printStackTrace();
        }
    }
}
