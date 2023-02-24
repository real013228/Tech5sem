package org.real013228.banks.Domain.Models.DepositHandlers;

import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.Abstractions.DepositCalculator;

public class ConcreteDepositHandler implements DepositCalculator {
    public ConcreteDepositHandler(double concretePercent, double bottomLine, double upperBound) {
        this.concretePercent = concretePercent;
        this.bottomLine = bottomLine;
        this.upperBound = upperBound;
    }

    private final double concretePercent;
    private final double bottomLine;
    private final double upperBound;
    @Getter
    @Setter
    private DepositCalculator nextHandler;

    @Override
    public double handleRequest(double value) {
        return value > bottomLine && value < upperBound ? concretePercent : nextHandler.handleRequest(value);
    }
}
