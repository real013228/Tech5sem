package org.real013228.banks.Domain.Entities.Commands;

import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Command;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;

public class AccrualAndDecreaseCommand implements Command {
    private final double takeMoney;
    private final double topUpMoney;
    private final BankAccount fromAccount;
    private final BankAccount toAccount;
    public AccrualAndDecreaseCommand(double takeMoney, double topUpMoney, BankAccount fromAccount, BankAccount toAccount) {
        this.takeMoney = takeMoney;
        this.topUpMoney = topUpMoney;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    @Override
    public void invoke() {
        fromAccount.accrualMoney(takeMoney);
        try {
            toAccount.decreaseMoney(topUpMoney);
        } catch (BalanceException e) {
            throw new RuntimeException(e);
        }
    }
}
