package org.real013228.banks.Domain.CustomExceptions;

/***
 * Balance-related exceptions
 */
public class BalanceException extends Exception{
    private BalanceException() {
    }

    private BalanceException(String msg) {
        super();
    }

    /***
     * invalid value exception
     * @param value - some value
     * @return throwing exception, if value isn't correct
     */
    public static BalanceException invalidValueException(double value) {
        return new BalanceException(String.format("Value is incorrect: %f", value));
    }
}
