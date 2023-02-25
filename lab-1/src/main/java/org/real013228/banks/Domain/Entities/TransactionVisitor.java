package org.real013228.banks.Domain.Entities;

import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Command;
import org.real013228.banks.Domain.Abstractions.Visitor;
import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.Commands.AccrualAndDecreaseCommand;
import org.real013228.banks.Domain.Entities.Commands.AccrualCommand;
import org.real013228.banks.Domain.Entities.Commands.DecreaseCommand;
import org.real013228.banks.Domain.Entities.Transactions.DecreaseMoney;
import org.real013228.banks.Domain.Entities.Transactions.IncreaseMoney;
import org.real013228.banks.Domain.Entities.Transactions.TransferMoney;

import java.util.List;

/***
 * Visitor implementation
 */
public class TransactionVisitor implements Visitor {
    private final List<BankAccount> accounts;
    @Getter
    @Setter
    private TransactionWrapper transaction;

    public TransactionVisitor(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void visit(DecreaseMoney transaction) throws TransactionException, AccountException, BalanceException {
        BankAccount account = accounts.stream().filter(x -> x.getId() == transaction.getAccount()).findFirst().orElse(null);
        if(account == null)
            throw TransactionException.nullAccountException();
        double tookMoney = account.takeMoney(transaction.getValue());
        Command cancel = new AccrualCommand(tookMoney, account);
        this.transaction = new TransactionWrapper(transaction, cancel);
    }

    @Override
    public void visit(IncreaseMoney transaction) throws TransactionException, AccountException {
        BankAccount account = accounts.stream().filter(x -> x.getId() == transaction.getAccount()).findFirst().orElse(null);
        if(account == null)
            throw TransactionException.nullAccountException();
        double topUpMoney = account.topUpMoney(transaction.getValue());
        Command cancel = new DecreaseCommand(topUpMoney, account);
        this.transaction = new TransactionWrapper(transaction, cancel);
    }

    @Override
    public void visit(TransferMoney transaction) throws TransactionException, AccountException, BalanceException {
        BankAccount fromAccount = accounts.stream().filter(x -> x.getId() == transaction.getFromAccount()).findFirst().orElse(null);
        BankAccount toAccount = accounts.stream().filter(x -> x.getId() == transaction.getToAccount()).findFirst().orElse(null);
        if (fromAccount == null || toAccount == null)
            throw TransactionException.nullAccountException();
        if (fromAccount.canTakeMoney(transaction.getValue()) && toAccount.canTopUpMoney(transaction.getValue())) {
            double takeMoney = fromAccount.takeMoney(transaction.getValue());
            double topUpMoney = toAccount.topUpMoney(transaction.getValue());
            var cancel = new AccrualAndDecreaseCommand(takeMoney, topUpMoney, fromAccount, toAccount);
            this.transaction = new TransactionWrapper(transaction, cancel);
        }

        throw TransactionException.invalidOperationException();
    }
}
