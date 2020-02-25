package mx.com.nacho.utils.test;

import mx.com.nacho.utils.DateOfBirth;
import org.junit.Test;

import java.util.Date;

public class DateOfBirthTest {
    @Test
    public void test1() {
        DateOfBirth dob = new DateOfBirth(1983, 3, 3);
        System.out.println(dob.toString());
    }

    @Test
    public void test2() {
        DateOfBirth dob = new DateOfBirth(new Date());
        System.out.println(dob.toString());
    }
}
