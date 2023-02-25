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
    private final Balance balanceValue;
    @Getter(AccessLevel.NONE)
    private final Bank bank;
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
    }

    private final NotifyStrategy notifier;
    private final Client clientAccount;
    @Setter(AccessLevel.PRIVATE)
    private double transactionLimit;
    private final double percent;

    /***
     * getter method
     * @return amount of money in balance
     */
    @Override
    public double getBalanceValue() {
        return balanceValue.getValue();
    }
    private UUID id;
    private Clock clock;
    @Setter(AccessLevel.PRIVATE)
    private int interval;

    /***
     * taking up money method
     * @param value - amount of taking money
     * @return amount of money if there are no any exceptions
     * @throws BalanceException balance exception
     */
    public double takeMoney(double value) throws BalanceException {
        return balanceValue.decreaseMoney(value);
    }

    /***
     * topping up money method
     * @param value amount of topping up money
     * @return amount of money if there are no any exceptions
     */
    public double topUpMoney(double value)
    {
        return balanceValue.increaseMoney(value);
    }

    /***
     * checking method
     * @param value how much money should be taken
     * @return true if it's possible to take money
     */
    public boolean canTakeMoney(double value)
    {
        return (!clientAccount.isSus() || transactionLimit >= value) && !isExpired && !(balanceValue.getValue() < value);
    }

    /***
     * checking method
     * @param value how much money should be topped up
     * @return true if it's possible to top up money
     */
    public boolean canTopUpMoney(double value)
    {
        return !clientAccount.isSus() || transactionLimit >= value;
    }

    /***
     * force increasing
     * @param value amount of money
     */
    public void accrualMoney(double value)
    {
        if (canTopUpMoney(value))
            balanceValue.increaseMoney(value);
    }

    /***
     * force decreasing
     * @param value amount of money
     * @throws BalanceException balance exception
     */
    public void decreaseMoney(double value) throws BalanceException {
        if (canTakeMoney(value))
            balanceValue.decreaseMoney(value);
    }

    /***
     * verifying method
     */
    private void verify()
    {
        isExpired = true;
    }

    /***
     * setter method, that works every day
     * @param t true
     */
    private void setMoneyEveryDay(boolean t)
    {
        interval--;
        if (interval == 0) {
            verify();
        }
        cashBack += balanceValue.getValue() * percent;
    }

    /***
     * increasing every month money
     * @param t true
     */
    private void increaseMoneyEveryMonth(boolean t)
    {
        balanceValue.increaseMoney(cashBack);
    }
}
