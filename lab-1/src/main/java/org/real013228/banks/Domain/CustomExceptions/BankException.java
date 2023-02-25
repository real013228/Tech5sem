package org.real013228.banks.Domain.CustomExceptions;

import java.util.UUID;

/***
 * Bank-related exceptions
 */
public class BankException extends Exception {
    private BankException() {
    }

    private BankException(String message) {
        super(message);
    }
    public static BankException invalidBankId(UUID id) {
        return new BankException("There is no any bank with id: " + id);
    }
    public static BankException clientAlreadyExist(UUID id) {
        return new BankException("There is already client with the same id: " + id);
    }
}
