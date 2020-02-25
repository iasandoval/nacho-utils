package mx.com.nacho.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
    private LocalDate dob;

    public DateOfBirth() {
        this.dob = LocalDate.now();
    }

    public DateOfBirth(int year, int month, int day) {
        this.dob = LocalDate.of(year, month, day);
    }

    public DateOfBirth(Date dob) {
        this.dob = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public DateOfBirth(Timestamp dob) {
        this.dob = dob.toLocalDateTime().toLocalDate();
    }

    public Date getDate() {
        return Date.from(this.dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Timestamp getTimestamp() {
        return Timestamp.from(this.dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate getLocalDate() {
        return this.dob;
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

    @Override
    public String toString() {
        return "DateOfBirth{" +
                "\ndob=" + this.dob +
                "\nage=" + this.getAge() +
                "\nisNewborn=" + this.isNewborn() +
                "\nisChild=" + this.isChild() +
                "\nisTeen=" + this.isTeen() +
                "\nisAdult=" + this.isAdult() +
                "\nisSenior=" + this.isSenior() +
                "\ncategory=" + this.getCategory() +
                "\nageRange=" + this.getAgeRange() +
                "\ndate=" + this.getDate() +
                "\ntimestamp=" + this.getTimestamp() +
                "\n}";
    }



}
