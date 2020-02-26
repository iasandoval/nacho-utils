package mx.com.nacho.utils.test;

import mx.com.nacho.utils.EncryptUtil;
import org.junit.Test;

public class EncryptUtilTest {
    @Test
    public void test() {

        EncryptUtil enc = new EncryptUtil().withKey("secret");

        String encrypted = enc.encrypt("nacho");
        String decrypted = enc.decrypt(encrypted);

        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
