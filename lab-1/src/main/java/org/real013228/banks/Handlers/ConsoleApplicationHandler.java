package org.real013228.banks.Handlers;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;

public interface ConsoleApplicationHandler {
    CentralBank getMainCentralBank();
    void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler);
    void setNextHandler(ConsoleApplicationHandler nextHandler);
    void handle(char key) throws TransactionException, BalanceException, ClientException, BankException;
}
