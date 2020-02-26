package mx.com.nacho.utils;

import java.util.regex.Pattern;

/**
 * Utility class to validate and format a Social Security Number.
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/">Validating Social Security Numbers through Regular Expressions</a>
 */
public class SocialSecurityNumber {

    private int ssn;

    public SocialSecurityNumber(int ssn) {
        this.ssn = ssn;
    }

    public boolean isValid() {
        Pattern ssnPattern = Pattern.compile("^(?!\\b(\\d)\\1+(\\d)\\1+(\\d)\\1+\\b)(?!123456789|219099999|078051120)(?!666|000|9\\d{2})\\d{3}(?!00)\\d{2}(?!0{4})\\d{4}$");
        return ssnPattern.matcher(this.getStringSSN()).matches();
    }

    public int getRawSSN() {
        return this.ssn;
    }

    public String getStringSSN() {
        return String.format("%09d", this.ssn);
    }

    public String getFormattedSSN() {
        return this.getStringSSN().replaceFirst("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
    }

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
