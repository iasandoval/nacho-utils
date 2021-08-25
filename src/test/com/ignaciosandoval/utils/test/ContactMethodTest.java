package com.ignaciosandoval.utils.test;

import com.ignaciosandoval.utils.contact.ContactMethod;
import com.ignaciosandoval.utils.contact.EmailAddress;
import com.ignaciosandoval.utils.contact.PhoneNumber;
import org.junit.Test;

public class ContactMethodTest {

    @Test
    public void testPhoneNumber() {
        ContactMethod phone = new PhoneNumber("AX(123) 456  78-90 ALKSDJ");

        System.out.println(phone);
    }

    @Test
    public void testEmailAddress() {
        ContactMethod email = new EmailAddress("NACHO@nacho.com");

        System.out.println(email);
    }

}
