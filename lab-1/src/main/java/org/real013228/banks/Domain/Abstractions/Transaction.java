package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;

import java.util.UUID;

/***
 * Transaction, that client can make in bank or central bank
 */
public interface Transaction {
    /***
     * accept method
     * @param visitor visitor, that transaction should accept
     */
    void accept(Visitor visitor) throws TransactionException, AccountException, BalanceException;

    /***
     * id getter method
     * @return id
     */
    UUID getId();
}
