package org.real013228.banks.Domain.CustomExceptions;

/***
 * Exceptions related with account
 */
public class AccountException extends Exception {
    private AccountException() {

    }

    private AccountException(String message) {
        super(message);
    }
    public static AccountException InvalidTakingMoneyException(double value) {
        return new AccountException(String.format("Invalid operation of taking money value %f",value));
    }
    public static AccountException InvalidToppingUpMoneyException(double value) {
        return new AccountException(String.format("Invalid operation of topping up money value %f", value));
    }
}
