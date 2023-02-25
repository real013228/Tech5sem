package org.real013228.banks.Domain.Entities.Actions;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.Action;

import java.util.function.Consumer;

public class TransactionLimitAction implements Action {
    @Getter
    private final Consumer<Double> commission;
    public TransactionLimitAction(Consumer<Double> value) {
        commission = value;
    }

    @Override
    public void invoke(Object obj) {
        commission.accept((Double) obj);
    }
}
