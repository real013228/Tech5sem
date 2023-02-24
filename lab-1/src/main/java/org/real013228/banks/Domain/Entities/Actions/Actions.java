package org.real013228.banks.Domain.Entities.Actions;

import org.real013228.banks.Domain.Abstractions.Action;

import java.util.List;

public class Actions {
    private List<Action> actions;
    public void invoke(Object acc) {
        for(var i : actions) {
            i.invoke(acc);
        }
    }
    public void addSubscriber(Action action) {
        actions.add(action);
    }
}
