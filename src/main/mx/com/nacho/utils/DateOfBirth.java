package mx.com.nacho.utils;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class to extract metadata from a Date Of Birth (DOB).
 *
 * @author iasandoval
 * @version 1.0
 */
public class DateOfBirth {

    /**
     * Date object to store the Date of Birth.
     */
    private LocalDate dob;

    /**
     * Default constructor. Will create a DOB from current system date.
     */
    public DateOfBirth() {
        this.dob = this.getToday();
    }

    /**
     * Constructor by year, month and day.
     *
     * @param year  Year to set in the Date of Birth.
     * @param month Month to set in the Date of Birth.
     * @param day   Day of the month to set in the Date of Birth.
     */
    public DateOfBirth(int year, int month, int day) {
        this.dob = LocalDate.of(year, month, day);
    }

    /**
     * Constructor by Date object.
     *
     * @param dob Date object representing the Date of Birth.
     * @see java.util.Date
     */
    public DateOfBirth(Date dob) {
        this.dob = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Constructor by Timestamp object.
     *
     * @param dob Timestamp object representing the Date of Birth.
     * @see java.sql.Timestamp
     */
    public DateOfBirth(Timestamp dob) {
        this.dob = dob.toLocalDateTime().toLocalDate();
    }

    /**
     * Get the Date of Birth as Date object.
     *
     * @return Date of Birth as Date object.
     * @see java.util.Date
     */
    public Date getDate() {
        return Date.from(this.dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get the Date of Birth as Timestamp object.
     *
     * @return Date of Birth as Timestamp object.
     * @see java.sql.Timestamp
     */
    public Timestamp getTimestamp() {
        return Timestamp.from(this.dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get the Date of Birth as LocalDate object.
     *
     * @return Date of Birth as LocalDate object.
     * @see java.time.LocalDate
     */
    public LocalDate getLocalDate() {
        return this.dob;
    }

    /**
     * Get the age based in the Date of Birth.
     *
     * @return Age based in the Date of Birth.
     */
    public int getAge() {
        return Period.between(this.getLocalDate(), this.getToday()).getYears();
    }

    /**
     * Will create a Date of Birth from current system date.
     *
     * @return Date of Birth from current system date.
     */
    private LocalDate getToday() {
        return LocalDate.now();
    }

    /**
     * Get the text of the month from the Date of Birth based in the system configuration.
     * For example, if the system is in Spanish, the month will be displayed in that language.
     *
     * @return The text of the month from the Date of Birth (Ex. Saturday/Sábado/etc.)
     */
    public String getMonth() {
        return this.dob.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    /**
     * Get the Day of Week from the Date of Birth based in the system configuration.
     * For example, if the system is in Spanish, the Date of Week will be displayed in that language.
     *
     * @return The text of the month from the Date of Birth (Ex. December/Diciembre/etc.).
     */
    public String getDayOfWeek() {
        return this.dob.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    /**
     * Indicates if the Date of Birth falls in a weekend.
     *
     * @return True if the Date of Birth falls in a weekend.
     */
    public boolean isWeekend() {
        DayOfWeek dow = this.dob.getDayOfWeek();
        return dow.equals(DayOfWeek.SATURDAY) || dow.equals(DayOfWeek.SUNDAY);
    }

    /**
     * Indicates if the age group belongs to a new born.
     *
     * @return True if the age is less than 1 year old.
     */
    public boolean isNewborn() {
        return this.getAge() < 1;
    }

    /**
     * Indicates if the age group belongs to a child.
     *
     * @return True if the age is between 1 to 12 inclusive.
     */
    public boolean isChild() {
        return this.getAge() >= 1 && this.getAge() <= 12;
    }

    /**
     * Indicates if the age group belongs to a teenager.
     *
     * @return True if the age is between 13 to 17 inclusive.
     */
    public boolean isTeen() {
        return this.getAge() >= 13 && this.getAge() <= 17;
    }

    /**
     * Indicates if the age group belongs to an adult.
     *
     * @return True if the age is more or equals to 18.
     */
    public boolean isAdult() {
        return this.getAge() >= 18;
    }

    /**
     * Indicates if the age group belongs to a senior.
     *
     * @return True if the age is more or equals to 65.
     */
    public boolean isSenior() {
        return this.getAge() >= 65;
    }

    /**
     * Gets the category based on the age group.
     *
     * @return Depending of the age, will return "SENIOR", "ADULT", "TEEN", "CHILD", "NEWBORN".
     */
    public String getCategory() {
        if (this.isSenior()) return "SENIOR";
        if (this.isAdult()) return "ADULT";
        if (this.isTeen()) return "TEEN";
        if (this.isChild()) return "CHILD";
        if (this.isNewborn()) return "NEWBORN";

        return "NA";
    }

    /**
     * Gets the age range from the Date of Birth.
     *
     * @return Depending of the age, will return "65+", "18+", "13 to 17", "1 to 12", "0".
     */
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
                "\n    dob=" + this.dob +
                "\n    age=" + this.getAge() +
                "\n    day=" + this.dob.getDayOfMonth() +
                "\n    month=" + this.dob.getMonth().getValue() +
                "\n    year=" + this.dob.getYear() +
                "\n    monthText=" + this.getMonth() +
                "\n    dayOfWeek=" + this.getDayOfWeek() +
                "\n    isWeekend=" + this.isWeekend() +
                "\n    isNewborn=" + this.isNewborn() +
                "\n    isChild=" + this.isChild() +
                "\n    isTeen=" + this.isTeen() +
                "\n    isAdult=" + this.isAdult() +
                "\n    isSenior=" + this.isSenior() +
                "\n    category=" + this.getCategory() +
                "\n    ageRange=" + this.getAgeRange() +
                "\n    date=" + this.getDate() +
                "\n    timestamp=" + this.getTimestamp() +
                "\n}";
    }
}
