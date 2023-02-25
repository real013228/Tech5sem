package org.real013228.banks.Handlers.CancelTransaction;

import lombok.Getter;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;
import org.real013228.banks.Domain.Abstractions.CentralBank;

import java.util.Scanner;
import java.util.UUID;

public class CancelTransactionHandler implements ConsoleApplicationHandler {
    private final Scanner scanner;
    public CancelTransactionHandler(Scanner scanner, CentralBank mainCentralBank) {
        this.scanner = scanner;
        this.mainCentralBank = mainCentralBank;
    }

    @Getter
    private final CentralBank mainCentralBank;

    @Override
    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler) {
    }

    @Override
    public void setNextHandler(ConsoleApplicationHandler nextHandler) {

    }

    @Override
    public void handle(char key) throws TransactionException, BalanceException {
        if (key == '5') {
            System.out.println("Please enter Id of transaction");
            String transactionId = scanner.nextLine();
            if (transactionId != null) {
                mainCentralBank.cancelTransaction(UUID.fromString(transactionId));
                System.out.println("Transaction has been cancelled successfully!");
            } else {
                System.out.println("Bad id of transaction, try again later!");
            }
        }

    }
}
