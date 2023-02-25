package org.real013228.banks.Handlers.HandlerAbstractions;

import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

public interface SetBankAccountParameter extends HandlerLessResponsibilities {
    Bank getBank();
    void setNextHandler(SetBankAccountParameter nextHandler);
}
