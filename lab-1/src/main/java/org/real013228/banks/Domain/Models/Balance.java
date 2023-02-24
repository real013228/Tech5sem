package org.real013228.banks.Domain.Models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;

/***
 * Clients balance model
 */
public class Balance {
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private double value;

    public Balance(double value) {
        this.value = value;
    }

    /***
     * Method, that increases your value of money
     * @param value amount of money
     * @return value
     */
    public double increaseMoney(double value) {
        this.value += value;
        return value;
    }

    /***
     * Method, that decreases your value of money
     * @param value amount of money
     * @return value
     * @throws BalanceException if value isn't correct
     */
    public double decreaseMoney(double value) throws BalanceException {
        if (this.value < value) {
            throw BalanceException.invalidValueException(value);
        }
        this.value -= value;
        return value;
    }
}
