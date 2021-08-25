package com.ignaciosandoval.utils.contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing Phone Number as a Contact Method.
 *
 * @author iasandoval
 * @version 1.0
 */
public class PhoneNumber implements ContactMethod {

    /**
     * Pattern for a 10 digits Phone Number.
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\d{10})$");

    /**
     * Pattern for a 3 or 2 digits Area Code for the Phone Number.
     */
    private static final Pattern PHONE_AREA_CODE_PATTERN = Pattern.compile("\\((\\d{3}|\\d{2})\\)");

    /**
     * The Phone Number.
     */
    private final String phone;

    /**
     * Indicates if the Phone Number has been verified.
     */
    private final boolean verified;

    /**
     * Constructor by Phone number.
     * <p>
     * Will validate if the provided Phone Number is not Empty, if so,
     * it will remove all characters except numbers from it (Cleansing).
     *
     * @param phoneNumber The Phone Number.
     */
    public PhoneNumber(String phoneNumber) {
        this.phone = (!ContactMethod.isEmpty(phoneNumber)) ? this.cleanPhoneNumber(phoneNumber) : "";
        this.verified = Boolean.FALSE;
    }

    /**
     * Constructor by Phone number and Verified value.
     * <p>
     * Will validate if the provided Phone Number is not Empty, if so,
     * it will remove all characters except numbers from it (Cleansing).
     *
     * @param phoneNumber The Phone Number.
     * @param verified    Indicates if the Phone Number is Verified.
     */
    public PhoneNumber(String phoneNumber, boolean verified) {
        this.phone = (!ContactMethod.isEmpty(phoneNumber)) ? this.cleanPhoneNumber(phoneNumber) : "";
        this.verified = verified;
    }

    /**
     * Method to clean a Phone Number.
     * Will remove all non-digits characters, keeping only numbers.
     *
     * @param phoneNumber THe Phone NUmber to clean.
     * @return The cleaned Phone Number.
     */
    private String cleanPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("\\D+", "");
    }

    /**
     * Returns the Phone Number in format (123) 456-7890.
     *
     * @return The formatted Phone Number.
     */
    @Override
    public String getFormatted() {
        String formattedPhone = "";

        if (!this.isEmpty() && this.isValid()) {
            formattedPhone = this.phone.replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "($1) $2-$3");
        }

        return formattedPhone;
    }

    /**
     * Returns the Phone Number in masked format (***) ***-7890.
     *
     * @return The masked Phone Number.
     */
    @Override
    public String getMasked() {
        String maskedPhone = "";

        if (!this.isEmpty() && this.isValid()) {
            maskedPhone = this.getFormatted().replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
        }

        return maskedPhone;
    }

    /**
     * Returns the Phone Number value.
     *
     * @return The Phone Number value.
     */
    @Override
    public String getValue() {
        return this.phone;
    }

    /**
     * Indicates if the Phone Number is verified.
     *
     * @return True if the Phone NUmber is verified.
     */
    @Override
    public boolean isVerified() {
        return this.verified;
    }

    /**
     * Indicates if the Phone Number is empty.
     *
     * @return True if the Phone Number is empty.
     */
    @Override
    public boolean isEmpty() {
        return ContactMethod.isEmpty(this.phone);
    }

    /**
     * Match the pattern for a 10 digits Phone Number.
     *
     * @return True if is a 10 digits Phone Number.
     */
    @Override
    public boolean isValid() {
        return PHONE_PATTERN.matcher(this.phone).matches();
    }

    /**
     * Getter for the class type.
     *
     * @return PhoneNumber (Class Name).
     */
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Getter for the 3 digits Area Code of the Phone Number.
     *
     * @return 3 digits Are Code.
     */
    public String getAreaCode() {
        String areaCode = "";

        if (this.isValid()) {
            Matcher areaCodeMatcher = PHONE_AREA_CODE_PATTERN.matcher(this.getFormatted());
            if (areaCodeMatcher.find()) {
                areaCode = areaCodeMatcher.group(1);
            }
        }

        return areaCode;
    }

    @Override
    public String toString() {
        return "PhoneNumber: {" +
                "\n\tvalue: \"" + this.getValue() + "\"," +
                "\n\tformatted: \"" + this.getFormatted() + "\"," +
                "\n\tareaCode: \"" + this.getAreaCode() + "\"," +
                "\n\tmasked: \"" + this.getMasked() + "\"," +
                "\n\tverified: " + this.isVerified() + "," +
                "\n\tisEmpty: " + this.isEmpty() + "," +
                "\n\tisValid: " + this.isValid() + "," +
                "\n\ttype: \"" + this.getType() + "\"" +
                "\n}";
    }
}
