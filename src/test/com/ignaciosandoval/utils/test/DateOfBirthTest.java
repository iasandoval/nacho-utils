package com.ignaciosandoval.utils.test;

import com.ignaciosandoval.utils.date.DateOfBirth;
import org.junit.Test;

import java.util.Date;

public class DateOfBirthTest {
    @Test
    public void test1() {
        DateOfBirth dob = new DateOfBirth(1983, 12, 3);
        System.out.println(dob);
    }

    @Test
    public void test2() {
        DateOfBirth dob = new DateOfBirth(new Date());
        System.out.println(dob);
    }
}
