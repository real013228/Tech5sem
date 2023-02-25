package org.real013228.banks.Handlers.CreateBank;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankParameter;
import org.real013228.banks.Handlers.HandlerAbstractions.SetUserHandler;

import java.util.Scanner;

public class SetDebitPercentHandler implements SetBankParameter {
    private SetBankParameter nextHandler;
    private CentralBank mainCentralBank;
    private Scanner scanner;
    private Bank.BankBuilder builder;

    public SetDebitPercentHandler(CentralBank mainCentralBank, Scanner scanner, Bank.BankBuilder builder) {
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
        builder.debitPercent(Double.parseDouble(value));
        while (true) {
            System.out.println("Please set transaction limit for your new bank");
            Double transactionLimit = Double.parseDouble(scanner.nextLine());
            if (transactionLimit != null) {
                System.out.println(String.format("Transaction limit has been set successfully! New value is %s", transactionLimit));
                nextHandler.handle(transactionLimit.toString());
                break;
            }

            System.out.println("Try again");
        }
    }
}
