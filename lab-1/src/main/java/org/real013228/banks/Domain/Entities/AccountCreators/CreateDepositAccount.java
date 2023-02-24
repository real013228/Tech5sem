package org.real013228.banks.Domain.Entities.AccountCreators;

import org.real013228.banks.Domain.Abstractions.*;
import org.real013228.banks.Domain.Entities.Accounts.DepositAccount;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;

public class CreateDepositAccount implements CreateBankAccount {
    private final Clock clock;
    private final DepositCalculator calculator;
    private final NotifyStrategy notifier;
    private Client client;
    private double account;
    private Bank bank;

    public CreateDepositAccount(Clock clock, DepositCalculator calculator, NotifyStrategy notifier) {
        this.clock = clock;
        this.calculator = calculator;
        this.notifier = notifier;
    }

    @Override
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void setAccount(double account) {
        this.account = account;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public BankAccount build() {
        return new DepositAccount(bank.calculateDepositPercent(calculator, account), account, client, clock, bank.getExpirationDays(), notifier, bank, bank.getTransactionLimit());
    }
}
