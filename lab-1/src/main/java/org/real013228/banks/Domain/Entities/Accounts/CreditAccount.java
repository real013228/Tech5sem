package org.real013228.banks.Domain.Entities.Accounts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.Entities.Actions.CommissionAction;
import org.real013228.banks.Domain.Entities.Actions.CreditLimitAction;
import org.real013228.banks.Domain.Entities.Actions.TransactionLimitAction;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Domain.Models.Balance;

import java.util.UUID;

/***
 * Credit account type
 */
@Getter
public class CreditAccount implements BankAccount {
    @Getter(AccessLevel.NONE)
    private final Balance balance;
    @Getter(AccessLevel.NONE)
    private final Bank bank;
    private final Client clientAccount;
    @Setter
    private NotifyStrategy notifier;
    @Setter(AccessLevel.PRIVATE)
    private double creditLimit;
    @Setter(AccessLevel.PRIVATE)
    private double transactionLimit;
    private final double percent;
    @Setter(AccessLevel.PRIVATE)
    private double commission;
    private final UUID id;
    private final Clock clock;
    public CreditAccount(double commission, double account, Client clientAccount, Clock clock, double creditLimit, Bank bank, double transactionLimit, NotifyStrategy notifier) {
        this.percent = 0;
        this.commission = commission;
        this.balance = new Balance(account);
        this.clientAccount = clientAccount;
        this.clock = clock;
        this.creditLimit = creditLimit;
        this.bank = bank;
        this.transactionLimit = transactionLimit;
        this.notifier = notifier;
        this.id = UUID.randomUUID();
        bank.getCommissionHasBeenChangedSubscribers().addSubscriber(new CommissionAction(this::setCommission));
        bank.getTransactionLimitHasBeenChanged().addSubscriber(new TransactionLimitAction(this::setTransactionLimit));
        bank.getCreditLimitHasBeenChangedSubscribers().addSubscriber(new CreditLimitAction(this::setCreditLimit));
    }

    /***
     * taking up money method
     * @param value - amount of taking money
     * @return amount of money if there are no any exceptions
     * @throws AccountException account exception
     * @throws BalanceException balance exception
     */
    @Override
    public double takeMoney(double value) throws AccountException, BalanceException {
        if (!canTakeMoney(value))
            throw AccountException.InvalidTakingMoneyException(value);
        return balance.getValue() < 0
                ? balance.decreaseMoney(value + commission)
                : balance.decreaseMoney(value);
    }

    /***
     * topping up money method
     * @param value amount of topping up money
     * @return amount of money if there are no any exceptions
     * @throws AccountException account exception
     */
    @Override
    public double topUpMoney(double value) throws AccountException {
        if (!canTopUpMoney(value))
            throw AccountException.InvalidToppingUpMoneyException(value);
        return balance.getValue() < 0
                ? balance.increaseMoney(value - commission)
                : balance.increaseMoney(value);
    }

    /***
     * checking method
     * @param value how much money should be taken
     * @return true if it's possible to take money
     */
    @Override
    public boolean canTakeMoney(double value) {
        return !clientAccount.isSus() || transactionLimit >= value
                && balance.getValue() - value - commission >= creditLimit;
    }

    /***
     * checking method
     * @param value how much money should be topped up
     * @return true if it's possible to top up money
     */
    @Override
    public boolean canTopUpMoney(double value) {
        return !clientAccount.isSus() || value <= transactionLimit;
    }

    /***
     * force increasing
     * @param value amount of money
     */
    @Override
    public void accrualMoney(double value) {
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
     * getter method
     * @return amount of money in current balance
     */
    @Override
    public double getBalanceValue() {
        return balance.getValue();
    }
}
