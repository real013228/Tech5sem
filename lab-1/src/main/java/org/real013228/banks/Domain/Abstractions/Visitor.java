package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.Transactions.DecreaseMoney;
import org.real013228.banks.Domain.Entities.Transactions.IncreaseMoney;
import org.real013228.banks.Domain.Entities.Transactions.TransferMoney;

/***
 * identifying transaction's type
 */
public interface Visitor {
    /***
     * identifying decreasing transaction type
     * @param transaction concrete transaction
     */
    void visit(DecreaseMoney transaction) throws TransactionException, AccountException, BalanceException;

    /***
     * identifying increasing transaction type
     * @param transaction concrete transaction
     */
    void visit(IncreaseMoney transaction) throws TransactionException, AccountException;

    /***
     * identifying transfer transaction type
     * @param transaction concrete transaction
     */
    void visit(TransferMoney transaction) throws TransactionException, AccountException, BalanceException;
}
