package org.real013228.banks.Domain.Entities.Transactions;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.Transaction;
import org.real013228.banks.Domain.Abstractions.Visitor;
import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;

import java.util.UUID;

/***
 * Increasing money type, that implements transaction interface
 */
@Getter
public class IncreaseMoney implements Transaction {
    private final UUID account;
    private final double value;
    private final UUID id;
    public IncreaseMoney(double value, UUID account) {
        this.value = value;
        this.account = account;
        this.id = UUID.randomUUID();
    }

    /***
     * accepting visitor method
     * @param visitor visitor, that transaction should accept
     */
    @Override
    public void accept(Visitor visitor) throws TransactionException, AccountException {
        visitor.visit(this);
    }
}
