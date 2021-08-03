package com.ignaciosandoval.utils.ssn;

import java.util.regex.Pattern;

/**
 * Utility class to validate and format a Social Security Number (SSN).
 * The validate method is using the Regular Expresion explained in the article
 * "Validating Social Security Numbers through Regular Expressions" by Rion Williams.
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2" target="_blank">Validating Social Security Numbers through Regular Expressions</a>
 */
public class SocialSecurityNumber {

    /**
     * Integer that represents the Social Security Number.
     */
    private int ssn;

    /**
     * Constructor by numeric SSN.
     *
     * @param ssn The Social Secutiry Number.
     */
    public SocialSecurityNumber(int ssn) {
        this.ssn = ssn;
    }

    /**
     * Validates the provided SSN based in the "Over-The-Top Validation" in the article
     * "Validating Social Security Numbers through Regular Expressions" by Rion Williams.
     *
     * @return True if the SSN provided is valid.
     * @see <a href="http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2" target="_blank">Validating Social Security Numbers through Regular Expressions</a>
     */
    public boolean isValid() {
        Pattern ssnPattern = Pattern.compile("^(?!\\b(\\d)\\1+(\\d)\\1+(\\d)\\1+\\b)(?!123456789|219099999|078051120)(?!666|000|9\\d{2})\\d{3}(?!00)\\d{2}(?!0{4})\\d{4}$");
        return ssnPattern.matcher(this.getStringSSN()).matches();
    }

    /**
     * Get the numeric representation of the SSN.
     *
     * @return The numeric representation of the SSN.
     */
    public int getRawSSN() {
        return this.ssn;
    }

    /**
     * Get the String representation of the SSN. If the SSN has less than 9 digits, it will fill with leading zeros.
     *
     * @return The String representation of the SSN.
     */
    public String getStringSSN() {
        return String.format("%09d", this.ssn);
    }

    /**
     * Get the formatted String representation of the SSN.
     * <BR><B>Format:</B> XXX-XX-XXXX
     *
     * @return The formatted String representation of the SSN.
     */
    public String getFormattedSSN() {
        return this.getStringSSN().replaceFirst("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
    }

    /**
     * Get the masked representation of the SSN.
     * <BR><B>Format:</B> XXX-XX-1234
     *
     * @return The masked representation of the SSN.
     */
    public String getMaskedSSN() {
        return this.getStringSSN().replaceFirst("(\\d{3})(\\d{2})(\\d{4})", "XXX-XX-$3");
    }

    @Override
    public String toString() {
        return "SocialSecurityNumber{" +
                "\n    ssn=" + this.ssn +
                "\n    isValid=" + this.isValid() +
                "\n    rawSSN=" + this.getRawSSN() +
                "\n    stringSSN=" + this.getStringSSN() +
                "\n    formattedSSN=" + this.getFormattedSSN() +
                "\n    maskedSSN=" + this.getMaskedSSN() +
                "\n}";
    }
}
