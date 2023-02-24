package org.real013228.banks.Handlers;

import org.real013228.banks.Domain.Abstractions.CentralBank;

public interface ConsoleApplicationHandler {
    CentralBank getMainCentralBank();
    void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler);
    void setNextHandler(ConsoleApplicationHandler nextHandler);
    void handle(char key);
}
