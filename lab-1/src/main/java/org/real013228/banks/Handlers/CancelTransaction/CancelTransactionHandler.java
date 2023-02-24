package org.real013228.banks.Handlers.CancelTransaction;

import lombok.Getter;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;
import org.real013228.banks.Domain.Abstractions.CentralBank;

public class CancelTransactionHandler implements ConsoleApplicationHandler {
    public CancelTransactionHandler(CentralBank mainCentralBank) {
        this.mainCentralBank = mainCentralBank;
    }

    @Getter
    private final CentralBank mainCentralBank;
    @Override
    public CentralBank getMainCentralBank() {
        return null;
    }

    @Override
    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler) {

    }

    @Override
    public void setNextHandler(ConsoleApplicationHandler nextHandler) {

    }

    @Override
    public void handle(char key) {

    }
}
