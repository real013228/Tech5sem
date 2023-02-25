package org.real013228.banks.Domain.Abstractions;

/***
 * deposit calculator interface
 */
public interface DepositCalculator {
    /***
     * getter method
     * @return next handler
     */
    DepositCalculator getNextHandler();

    /***
     * setter method
     * @param nextHandler handler that should be set
     */
    void setNextHandler(DepositCalculator nextHandler);

    /***
     * handle method
     * @param value value of signature
     * @return double if there aren't any exceptions
     */
    double handleRequest(double value);
}
