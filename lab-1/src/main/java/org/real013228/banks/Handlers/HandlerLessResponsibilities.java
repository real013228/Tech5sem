package org.real013228.banks.Handlers;

import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;

public interface HandlerLessResponsibilities {
    void handle(String value) throws ClientException, BankException;
}
