package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.CustomExceptions.BalanceException;

public interface Command {
    void invoke() throws BalanceException;
}
