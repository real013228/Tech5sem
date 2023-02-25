package org.real013228.banks.Domain.Entities.Accounts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.Entities.Actions.DayChangedAction;
import org.real013228.banks.Domain.Entities.Actions.MonthChangedAction;
import org.real013228.banks.Domain.Entities.Actions.TransactionLimitAction;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Domain.Models.Balance;

import java.util.UUID;

/***
 * Debit account type
 */
@Getter
public class DebitAccount implements BankAccount {
    @Getter(AccessLevel.NONE)
    private final Balance balance;
    @Getter(AccessLevel.NONE)
    private final Bank bank;
    @Getter(AccessLevel.NONE)
    private double cashBack;
    public DebitAccount(double percent, double account, Client clientAccount, Clock clock, NotifyStrategy notifier, double transactionLimit, Bank bank)
    {
        this.percent = percent;
        this.balance = new Balance(account);
        this.clientAccount = clientAccount;
        this.clock = clock;
        this.notifier = notifier;
        this.transactionLimit = transactionLimit;
        this.bank = bank;
        this.id = UUID.randomUUID();
        this.bank.getTransactionLimitHasBeenChanged().addSubscriber(new TransactionLimitAction(this::setTransactionLimit));
        this.clock.getDayChangedSubscribers().addSubscriber(new DayChangedAction(this::setMoneyEveryDay));
        this.clock.getMonthChangedSubscribers().addSubscriber(new MonthChangedAction(this::increaseMoneyEveryMonth));
    }

    @Setter
    private NotifyStrategy notifier;
    private final Client clientAccount;
    @Setter(AccessLevel.PRIVATE)
    private double transactionLimit;
    private final double percent;
    @Override
    public double getBalanceValue() {
        return balance.getValue();
    }
    private final UUID id;
    private final Clock clock;

    @Override
    public double takeMoney(double value) throws BalanceException {
        return balance.decreaseMoney(value);
    }
    @Override
    public double topUpMoney(double value)
    {
        return balance.increaseMoney(value);
    }
    @Override
    public boolean canTakeMoney(double value)
    {
        return !clientAccount.isSus() || transactionLimit >= value || balance.getValue() >= value;
    }
    @Override
    public boolean canTopUpMoney(double value)
    {
        return !clientAccount.isSus() || transactionLimit >= value;
    }
    @Override
    public void accrualMoney(double value)
    {
        balance.increaseMoney(value);
    }
    @Override
    public void decreaseMoney(double value) throws BalanceException {
        balance.decreaseMoney(value);
    }
    private void setMoneyEveryDay(boolean value)
    {
        cashBack += balance.getValue() * percent;
    }

    private void increaseMoneyEveryMonth(boolean value)
    {
        balance.increaseMoney(cashBack);
    }
}
