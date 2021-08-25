package com.ignaciosandoval.utils.contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing an Email Address as a Contact Method.
 *
 * @author iasandoval
 * @version 1.0
 */
public class EmailAddress implements ContactMethod {

    /**
     * Pattern for a common Email Address.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

    /**
     * Pattern to extract the Domain from an Email Address.
     */
    private static final Pattern EMAIL_DOMAIN_PATTERN = Pattern.compile("@([a-zA-Z0-9_\\-\\.]+)");

    /**
     * Pattern to extract the Username from an Email Address.
     */
    private static final Pattern EMAIL_USERNAME_PATTERN = Pattern.compile("([a-zA-Z0-9_\\-\\.]+)@");

    /**
     * The Email Address.
     */
    private final String email;

    /**
     * Indicates if the Email Address has been verified.
     */
    private final boolean verified;

    /**
     * Constructor by Email Address.
     * <p>
     * Will validate if the provided Email Address is not empty.
     *
     * @param emailAddress The Email Address.
     */
    public EmailAddress(String emailAddress) {
        this.email = (!ContactMethod.isEmpty(emailAddress)) ? emailAddress : "";
        this.verified = Boolean.FALSE;
    }

    /**
     * Constructor by Email Address and Verified value.
     * <p>
     * Will validate if the provided Email Address is not empty.
     *
     * @param emailAddress The Email Address.
     * @param verified     Indicates if the Email Address is Verified.
     */
    public EmailAddress(String emailAddress, boolean verified) {
        this.email = (!ContactMethod.isEmpty(emailAddress)) ? emailAddress : "";
        this.verified = verified;
    }

    /**
     * Returns the Email Address in lower case format.
     *
     * @return The formatted Email Address.
     */
    @Override
    public String getFormatted() {
        return (this.isValid()) ? this.email.toLowerCase() : "";
    }

    /**
     * Returns the Email Address is masked format. Ex. na***@nacho.com.
     *
     * @return The masked Email Address.
     */
    @Override
    public String getMasked() {
        String maskedEmail = "";

        if (!this.isEmpty() && this.isValid()) {
            maskedEmail = this.getFormatted().replaceAll("(^[^@]{2}|(?!^)\\G)[^@]", "$1*");
        }

        return maskedEmail;
    }

    /**
     * Returns the Email Address value.
     *
     * @return The Email Address value.
     */
    @Override
    public String getValue() {
        return this.email;
    }

    /**
     * Indicates if the Email Address is verified.
     *
     * @return True if the Email Address is verified.
     */
    @Override
    public boolean isVerified() {
        return this.verified;
    }

    /**
     * Indicates if the Email Address is empty.
     *
     * @return True if the Email Address is empty.
     */
    @Override
    public boolean isEmpty() {
        return ContactMethod.isEmpty(this.email);
    }

    /**
     * Match the pattern for a common Email Address.
     *
     * @return True if the Email Address matches the pattern.
     */
    @Override
    public boolean isValid() {
        return EMAIL_PATTERN.matcher(this.email).matches();
    }

    /**
     * Getter for the class type.
     *
     * @return EmailAddress (Class Name).
     */
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Getter to extract the domain from the Email Address.
     *
     * @return The domain from the Email Address.
     */
    public String getDomain() {
        String domain = "";

        if (this.isValid()) {
            Matcher domainMatcher = EMAIL_DOMAIN_PATTERN.matcher(this.getFormatted());
            if (domainMatcher.find()) {
                domain = domainMatcher.group(1);
            }
        }

        return domain;
    }

    /**
     * Getter to extract the Username from the Email Address.
     *
     * @return The Username from the Email Address.
     */
    public String getUserName() {
        String userName = "";

        if (this.isValid()) {
            Matcher userNameMatcher = EMAIL_USERNAME_PATTERN.matcher(this.getFormatted());
            if (userNameMatcher.find()) {
                userName = userNameMatcher.group(1);
            }
        }

        return userName;
    }

    @Override
    public String toString() {
        return "EmailAddress: {" +
                "\n\tvalue: \"" + this.getValue() + "\"," +
                "\n\tformatted: \"" + this.getFormatted() + "\"," +
                "\n\tuserName: \"" + this.getUserName() + "\"," +
                "\n\tdomain: \"" + this.getDomain() + "\"," +
                "\n\tmasked: \"" + this.getMasked() + "\"," +
                "\n\tverified: " + this.isVerified() + "," +
                "\n\tisEmpty: " + this.isEmpty() + "," +
                "\n\tisValid: " + this.isValid() + "," +
                "\n\ttype: \"" + this.getType() + "\"" +
                "\n}";
    }
}
