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
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Domain.Models.Balance;

import java.util.UUID;

/***
 * Deposit account type
 */
@Getter
public class DepositAccount implements BankAccount {
    @Getter(AccessLevel.NONE)
    private Balance balanceValue;
    @Getter(AccessLevel.NONE)
    private Bank bank;
    @Getter(AccessLevel.NONE)
    private boolean isExpired;
    @Getter(AccessLevel.NONE)
    private double cashBack;

    public DepositAccount(double percent, double startAccount, Client clientAccount, Clock clock, int interval, NotifyStrategy notifier, Bank bank, double transactionLimit)
    {
        this.isExpired = false;
        this.percent = percent;
        this.balanceValue = new Balance(startAccount);
        this.clientAccount = clientAccount;
        this.clock = clock;
        this.id = UUID.randomUUID();
        cashBack = 0;
        this.interval = interval;
        this.notifier = notifier;
        this.bank = bank;
        this.transactionLimit = transactionLimit;
        clock.getDayChangedSubscribers().addSubscriber(new DayChangedAction(this::setMoneyEveryDay));
        clock.getMonthChangedSubscribers().addSubscriber(new MonthChangedAction(this::increaseMoneyEveryMonth));
//        clock.TimeHasBeenExpired += Verify;
//        clock.DayHasBeenPassed += SetMoneyEveryDay;
//        clock.MonthHasBeenPassed += IncreaseMoneyEveryMonth;
    }

    private final NotifyStrategy notifier;
    private final Client clientAccount;
    @Setter(AccessLevel.PRIVATE)
    private double transactionLimit;
    private final double percent;
    @Override
    public double getBalanceValue() {
        return balanceValue.getValue();
    }
    private UUID id;
    private Clock clock;
    @Setter(AccessLevel.PRIVATE)
    private int interval;

    public double takeMoney(double value) throws BalanceException {
        return balanceValue.decreaseMoney(value);
    }

    public double topUpMoney(double value)
    {
        return balanceValue.increaseMoney(value);
    }

    public boolean canTakeMoney(double value)
    {
        return (!clientAccount.isSus() || transactionLimit >= value) && !isExpired && !(balanceValue.getValue() < value);
    }

    public boolean canTopUpMoney(double value)
    {
        return !clientAccount.isSus() || transactionLimit >= value;
    }

    public void accrualMoney(double value)
    {
        if (canTopUpMoney(value))
            balanceValue.increaseMoney(value);
    }

    public void decreaseMoney(double value) throws BalanceException {
        if (canTakeMoney(value))
            balanceValue.decreaseMoney(value);
    }

    private void verify()
    {
        isExpired = true;
    }

    private void setMoneyEveryDay(boolean t)
    {
        interval--;
        if (interval == 0) {
            verify();
        }
        cashBack += balanceValue.getValue() * percent;
    }

    private void increaseMoneyEveryMonth(boolean t)
    {
        balanceValue.increaseMoney(cashBack);
    }
}
