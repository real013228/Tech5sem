package org.real013228.banks.Domain.Entities;

import lombok.Getter;
import lombok.SneakyThrows;
import org.real013228.banks.Domain.Abstractions.Command;
import org.real013228.banks.Domain.Abstractions.Transaction;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;

/***
 * Transaction wrapper type
 */
public class TransactionWrapper {
    private final Command cancelTransaction;
    @Getter
    private final Transaction transaction;
    public TransactionWrapper(Transaction transaction, Command cancelTransaction) {
        this.transaction = transaction;
        this.cancelTransaction = cancelTransaction;
    }
    public void cancelTransaction() throws BalanceException {
        cancelTransaction.invoke();
    }
    @SneakyThrows
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TransactionWrapper otherWrapper))
            throw TransactionException.invalidCastToWrapperException();
        return transaction.getId() == ((TransactionWrapper) other).transaction.getId();
    }
}
