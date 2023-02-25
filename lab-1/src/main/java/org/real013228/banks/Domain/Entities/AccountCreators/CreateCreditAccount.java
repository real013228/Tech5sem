package org.real013228.banks.Domain.Entities.AccountCreators;

import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.CreateBankAccount;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.Entities.Accounts.CreditAccount;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;

/***
 * Credit account creator type
 */
public class CreateCreditAccount implements CreateBankAccount {
    private final Clock clock;
    private final NotifyStrategy notifier;
    private Client client;
    private double account;
    private Bank bank;

    public CreateCreditAccount(Clock clock, NotifyStrategy notifier) {
        this.clock = clock;
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
        return new CreditAccount(bank.getCommission(), account, client, clock, bank.getCreditLimit(), bank, bank.getTransactionLimit(), notifier);
    }
}
