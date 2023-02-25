package org.real013228.banks.Handlers.HandlerAbstractions;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

public interface SetBankParameter extends HandlerLessResponsibilities {
    CentralBank getMainCentralBank();

    Bank.BankBuilder getBuilder();
    void setNextHandler(SetBankParameter nextHandler);
}
