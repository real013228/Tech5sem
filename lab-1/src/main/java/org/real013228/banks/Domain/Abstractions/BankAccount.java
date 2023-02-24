package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.CustomExceptions.AccountException;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.Entities.Client;

import java.util.UUID;

/***
 * Client's bank account
 */
public interface BankAccount {
    /***
     * takes money from bank account
     * @param value - amount of taking money
     * @return amount of taken money with commission
     */
    double takeMoney(double value) throws AccountException, BalanceException;

    /***
     * topping up money into bank account
     * @param value amount of topping up money
     * @return amount of topped up money
     */
    double topUpMoney(double value) throws AccountException;

    /***
     * checks the possibility of withdrawing
     * @param value how much money should be taken
     * @return true if amount more than value, otherwise returns false
     */
    boolean canTakeMoney(double value);

    /***
     * checks the possibility of topping up
     * @param value how much money should be topped up
     * @return true if value less than transaction limit and client isn't suspect
     */
    boolean canTopUpMoney(double value);

    /***
     * force increasing of account money
     * @param value amount of money
     */
    void accrualMoney(double value);

    /***
     * force decreasing of account money
     * @param value amount of money
     */
    void decreaseMoney(double value) throws BalanceException;

    /***
     * id getter method
     * @return account id
     */
    UUID getId();

    /***
     * notifier getter method
     * @return notifier
     */
    NotifyStrategy getNotifier();

    /***
     * client account getter method
     * @return client account
     */
    Client getClientAccount();

    /***
     * transaction limit getter method
     * @return transaction limit
     */
    double getTransactionLimit();

    /***
     * balance value getter method
     * @return balance value
     */
    double getBalanceValue();

    /***
     * clock getter method
     * @return clock
     */
    Clock getClock();
}
