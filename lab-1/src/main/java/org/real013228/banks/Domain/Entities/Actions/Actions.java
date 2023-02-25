package org.real013228.banks.Domain.Entities.Actions;

import org.real013228.banks.Domain.Abstractions.Action;

import java.util.ArrayList;
import java.util.List;

/***
 * Custom actions collection type
 */
public class Actions {
    private List<Action> actions = new ArrayList<>();
    public void invoke(Object acc) {
        for(var i : actions) {
            i.invoke(acc);
        }
    }
    public void addSubscriber(Action action) {
        actions.add(action);
    }
}
