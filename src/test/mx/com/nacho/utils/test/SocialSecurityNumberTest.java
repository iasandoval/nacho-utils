package mx.com.nacho.utils.test;

import mx.com.nacho.utils.SocialSecurityNumber;
import org.junit.Assert;
import org.junit.Test;

public class SocialSecurityNumberTest {

    @Test
    public void testValid() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(52063811);
        System.out.println(ssn.toString());
        Assert.assertEquals(Boolean.TRUE, ssn.isValid());
    }

    @Test
    public void testInvalid() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(123456789);
        System.out.println(ssn.toString());
        Assert.assertEquals(Boolean.FALSE, ssn.isValid());
    }
}
