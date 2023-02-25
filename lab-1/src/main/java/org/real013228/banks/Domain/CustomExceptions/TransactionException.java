package org.real013228.banks.Domain.CustomExceptions;

/***
 * Exceptions, that are related with transactions
 */
public class TransactionException extends Exception {
    private TransactionException() {
    }

    private TransactionException(String message) {
        super(message);
    }
    public static TransactionException invalidCastToWrapperException() {
        return new TransactionException("Invalid cast to transaction wrapper");
    }
    public static TransactionException nullAccountException() {
        return new TransactionException("Null reference exception, invalid account");
    }
    public static TransactionException invalidOperationException() {
        return new TransactionException("Can't make transaction, invalid operation");
    }
}
