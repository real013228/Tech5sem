package org.real013228.banks.Domain.Entities.Commands;

import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Command;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;

public class DecreaseCommand implements Command {
    private final double value;
    private final BankAccount account;
    public DecreaseCommand(double value, BankAccount account) {
        this.value = value;
        this.account = account;
    }

    @Override
    public void invoke() throws BalanceException {
        account.decreaseMoney(value);
    }
}

