package org.real013228.banks.Domain.Abstractions;

public interface DepositCalculator {
    DepositCalculator getNextHandler();
    void setNextHandler(DepositCalculator nextHandler);
    double handleRequest(double value);
}
