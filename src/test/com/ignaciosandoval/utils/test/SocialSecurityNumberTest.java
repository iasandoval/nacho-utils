package com.ignaciosandoval.utils.test;

import com.ignaciosandoval.utils.ssn.SocialSecurityNumber;
import org.junit.Assert;
import org.junit.Test;

public class SocialSecurityNumberTest {

    @Test
    public void testValid() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(52063811);
        System.out.println(ssn);
        Assert.assertEquals(Boolean.TRUE, ssn.isValid());
    }

    @Test
    public void testInvalid() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(123456789);
        System.out.println(ssn);
        Assert.assertEquals(Boolean.FALSE, ssn.isValid());
    }
}
