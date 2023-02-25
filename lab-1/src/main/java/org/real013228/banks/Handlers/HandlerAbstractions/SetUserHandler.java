package org.real013228.banks.Handlers.HandlerAbstractions;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

public interface SetUserHandler extends HandlerLessResponsibilities {
    CentralBank getCentralBank();
    Bank getBank();
    Client.ClientBuilder getBuilder();
    void setNextHandler(SetUserHandler nextHandler);
}
