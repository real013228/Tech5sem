package org.real013228.banks.Domain.Entities.Actions;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.Action;

import java.util.function.Consumer;

public class MonthChangedAction implements Action {
    @Getter
    private final Consumer<Boolean> monthChanged;

    public MonthChangedAction(Consumer<Boolean> monthChanged) {
        this.monthChanged = monthChanged;
    }

    @Override
    public void invoke(Object obj) {
        monthChanged.accept(true);
    }
}
