package org.real013228.banks.Domain.Entities.Commands;

import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Command;

public class AccrualCommand implements Command {
    private final double value;
    private final BankAccount account;
    public AccrualCommand(double value, BankAccount account) {
        this.value = value;
        this.account = account;
    }

    @Override
    public void invoke() {
        account.accrualMoney(value);
    }
}
