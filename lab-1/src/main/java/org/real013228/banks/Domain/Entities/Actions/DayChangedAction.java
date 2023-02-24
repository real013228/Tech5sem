package org.real013228.banks.Domain.Entities.Actions;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.Action;

import java.util.function.Consumer;

public class DayChangedAction implements Action {
    @Getter
    private final Consumer<Boolean> dayChanged;

    public DayChangedAction(Consumer<Boolean> dayChanged) {
        this.dayChanged = dayChanged;
    }

    @Override
    public void invoke(Object obj) {
        dayChanged.accept(true);
    }
}
