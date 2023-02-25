package org.real013228.banks.Domain.CustomExceptions;

/***
 * Passport-related exceptions
 */
public class PassportException extends Exception{
    private PassportException() {
    }

    private PassportException(String message) {
        super(message);
    }

    /***
     * Invalid passport exception
     * @param passport passport number
     * @return throwing exception, if passport number isn't correct
     */
    public static PassportException invalidPassportException(String passport) {
        return new PassportException(String.format("Invalid passport: %s", passport));
    }
}
