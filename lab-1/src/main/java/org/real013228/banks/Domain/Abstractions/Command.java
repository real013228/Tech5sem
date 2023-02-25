package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.CustomExceptions.BalanceException;

/***
 * command interface
 */
public interface Command {
    /***
     * invoke method
     * @throws BalanceException throws balance exception
     */
    void invoke() throws BalanceException;
}
