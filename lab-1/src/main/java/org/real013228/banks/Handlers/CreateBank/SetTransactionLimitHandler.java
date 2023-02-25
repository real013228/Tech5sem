package org.real013228.banks.Handlers.CreateBank;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankParameter;

import java.util.Scanner;

public class SetTransactionLimitHandler implements SetBankParameter {
    private SetBankParameter nextHandler;
    private CentralBank mainCentralBank;
    private Scanner scanner;
    private Bank.BankBuilder builder;

    public SetTransactionLimitHandler(CentralBank mainCentralBank, Scanner scanner, Bank.BankBuilder builder) {
        this.mainCentralBank = mainCentralBank;
        this.scanner = scanner;
        this.builder = builder;
    }

    @Override
    public CentralBank getMainCentralBank() {
        return mainCentralBank;
    }

    @Override
    public Bank.BankBuilder getBuilder() {
        return builder;
    }

    @Override
    public void setNextHandler(SetBankParameter nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(String value) throws BankException, ClientException {
        builder.transactionLimit(Double.parseDouble(value));
        while (true) {
            System.out.println("Please set deposit account term for your new bank");
            Integer timeInterval = Integer.parseInt(scanner.nextLine());
            if (timeInterval != null) {
                System.out.println(String.format("Deposit account term has been set successfully! New value is %s", timeInterval));
                nextHandler.handle(timeInterval.toString());
                break;
            }

            System.out.println("Try again");
        }
    }
}
