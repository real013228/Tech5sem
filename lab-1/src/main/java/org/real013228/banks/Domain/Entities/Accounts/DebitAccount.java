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

    /***
     * getter method
     * @return current amount of money
     */
    @Override
    public double getBalanceValue() {
        return balance.getValue();
    }
    private final UUID id;
    private final Clock clock;

    /***
     * taking up money method
     * @param value - amount of taking money
     * @return amount of money if there are no any exceptions
     * @throws BalanceException balance exception
     */
    @Override
    public double takeMoney(double value) throws BalanceException {
        return balance.decreaseMoney(value);
    }

    /***
     * topping up money method
     * @param value amount of topping up money
     * @return amount of money if there are no any exceptions
     */
    @Override
    public double topUpMoney(double value)
    {
        return balance.increaseMoney(value);
    }

    /***
     * checking method
     * @param value how much money should be taken
     * @return true if it's possible to take money
     */
    @Override
    public boolean canTakeMoney(double value)
    {
        return !clientAccount.isSus() || transactionLimit >= value || balance.getValue() >= value;
    }

    /***
     * checking method
     * @param value how much money should be topped up
     * @return true if it's possible to top up money
     */
    @Override
    public boolean canTopUpMoney(double value)
    {
        return !clientAccount.isSus() || transactionLimit >= value;
    }

    /***
     * force increasing
     * @param value amount of money
     */
    @Override
    public void accrualMoney(double value)
    {
        balance.increaseMoney(value);
    }

    /***
     * force decreasing
     * @param value amount of money
     * @throws BalanceException
     */
    @Override
    public void decreaseMoney(double value) throws BalanceException {
        balance.decreaseMoney(value);
    }

    /***
     * setter method, that works every day
     * @param value value
     */
    private void setMoneyEveryDay(boolean value)
    {
        cashBack += balance.getValue() * percent;
    }

    /***
     * increasing every month money
     * @param value
     */
    private void increaseMoneyEveryMonth(boolean value)
    {
        balance.increaseMoney(cashBack);
    }
}
