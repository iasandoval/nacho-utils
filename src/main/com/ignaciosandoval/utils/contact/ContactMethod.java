package com.ignaciosandoval.utils.contact;

/**
 * Interface defining a Contact Method.
 *
 * @author iasandoval
 * @version 1.0
 */
public interface ContactMethod {

    /**
     * Returns the formatted Contact Method.
     *
     * @return The formatted Contact Method.
     */
    String getFormatted();

    /**
     * Returns the masked Contact Method.
     *
     * @return The masked Contact Method.
     */
    String getMasked();

    /**
     * Returns the Contact Method value.
     *
     * @return The Contact Method value.
     */
    String getValue();

    /**
     * Indicates if the Contact Method is verified.
     *
     * @return True if the Contact Method is verified.
     */
    boolean isVerified();

    /**
     * Indicates if the Contact Method is empty.
     *
     * @return True if the Contact Method is empty.
     */
    boolean isEmpty();

    /**
     * Indicates if the Contact Method is valid.
     *
     * @return True if the Contact Method is valid.
     */
    boolean isValid();

    /**
     * Getter for the class type.
     *
     * @return The class name.
     */
    String getType();

    /**
     * Helper method to validate if a String is Empty.
     *
     * @param contactMethod The Contact Method to validate if is empty.
     * @return True if the provided contactMethod is empty.
     */
    static boolean isEmpty(String contactMethod) {
        return !(contactMethod != null && contactMethod.trim().length() > 0);
    }
}
