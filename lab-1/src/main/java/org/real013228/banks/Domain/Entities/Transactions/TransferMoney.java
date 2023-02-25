package org.real013228.banks.Domain.Entities.Transactions;


import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.Transaction;
import org.real013228.banks.Domain.Abstractions.Visitor;
import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;

import java.util.UUID;

/***
 * Transferring money type, that implements Transaction interface
 */
@Getter
public class TransferMoney implements Transaction {
    private final UUID fromAccount;
    private final UUID toAccount;
    private final double value;
    private final UUID id;
    public TransferMoney(double value, UUID toAccount, UUID fromAccount) {
        this.value = value;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.id = UUID.randomUUID();
    }

    /***
     * accepting visitor method
     * @param visitor visitor, that transaction should accept
     */
    @Override
    public void accept(Visitor visitor) throws TransactionException, AccountException, BalanceException {
        visitor.visit(this);
    }
}