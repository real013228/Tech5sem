package org.real013228.banks.Domain.Entities.Commands;

import org.real013228.banks.Domain.Abstractions.Command;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;

import java.util.List;

public class CommandContainer {
    private List<Command> commands;
    public void invoke() throws BalanceException {
        for(var i : commands)  {
            i.invoke();
        }
    }
    public void addSubscriber(Command command) {
        commands.add(command);
    }
}
