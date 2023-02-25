package org.real013228.banks.Handlers.CreateBank;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankParameter;
import org.real013228.banks.Handlers.HandlerAbstractions.SetUserHandler;

import java.io.Console;
import java.util.Scanner;
import java.util.Set;

public class SetCreditLimitHandler implements SetBankParameter {
    private SetBankParameter nextHandler;
    private CentralBank mainCentralBank;
    private Scanner scanner;
    private Bank.BankBuilder builder;

    public SetCreditLimitHandler(CentralBank mainCentralBank, Scanner scanner, Bank.BankBuilder builder) {
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
        builder.creditLimit(Double.parseDouble(value));
        while (true) {
            System.out.println("Please set debit percent for your new bank");
            Double debitPercent = Double.parseDouble(scanner.nextLine());
            if (debitPercent != null) {
                System.out.println(String.format("Debit percent has been set successfully! New value is %s", debitPercent));
                nextHandler.handle(debitPercent.toString());
                break;
            }

            System.out.println("Try again");
        }
    }
}
