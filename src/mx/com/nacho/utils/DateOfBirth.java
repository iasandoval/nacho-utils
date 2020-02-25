package mx.com.nacho.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * Utility class to handle some of the scenarios while doing operations with a Date Of Birth (DOB).
 * @author iasandoval
 * @version 1.0
 */
public class DateOfBirth {

    /**
     * Date object to store the Date of Birth.
     */
    private Date dob;

    public DateOfBirth() {
        this.dob = new Date();
    }

    public DateOfBirth(Date dob) {
        this.dob = dob;
    }

    public DateOfBirth(Timestamp dob) {
        this.dob = new Date(dob.getTime());
    }

    public Date getDate() {
        return this.dob;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(this.dob.getTime());
    }

    public LocalDate getLocalDate() {
        return new java.sql.Date(this.dob.getTime()).toLocalDate();
    }

    public int getAge() {
        return Period.between(this.getLocalDate(), this.getToday()).getYears();
    }

    private LocalDate getToday() {
        return new java.sql.Date(new Date().getTime()).toLocalDate();
    }

    public boolean isNewborn() {
        return this.getAge() < 1;
    }

    public boolean isChild() {
        return this.getAge() >= 1 && this.getAge() <= 12;
    }

    public boolean isTeen() {
        return this.getAge() >= 13 && this.getAge() <= 17;
    }

    public boolean isAdult() {
        return this.getAge() >= 18;
    }

    public boolean isSenior() {
        return this.getAge() >= 65;
    }

    public String getCategory() {
        if (this.isSenior()) return "SENIOR";
        if (this.isAdult()) return "ADULT";
        if (this.isTeen()) return "TEEN";
        if (this.isChild()) return "CHILD";
        if (this.isNewborn()) return "NEWBORN";

        return "NA";
    }

    public String getAgeRange() {
        if (this.isSenior()) return "65+";
        if (this.isAdult()) return "18+";
        if (this.isTeen()) return "13 to 17";
        if (this.isChild()) return "1 to 12";
        if (this.isNewborn()) return "0";

        return "NA";
    }

}
