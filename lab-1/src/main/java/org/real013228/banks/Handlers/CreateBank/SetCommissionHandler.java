package org.real013228.banks.Handlers.CreateBank;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Bank.BankBuilder;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankParameter;

import java.util.Scanner;
public class SetCommissionHandler implements SetBankParameter{
    private Scanner scanner;
    private SetBankParameter nextHandler;
    @Getter
    private CentralBank mainCentralBank;
    @Getter
    private BankBuilder builder;

    public SetCommissionHandler(CentralBank mainCentralBank, BankBuilder builder, Scanner scanner) {
        this.mainCentralBank = mainCentralBank;
        this.builder = builder;
        this.scanner = scanner;
    }
    public void setNextHandler(SetBankParameter nextHandler) {
        this.nextHandler = nextHandler;
    }
    public void handle(String value) throws BankException, ClientException {
        builder.commission(Double.parseDouble(value));
        while(true) {
            System.out.println("Please set credit limit for your new bank");
            String creditLimit = scanner.nextLine();
            if (creditLimit != null) {
                System.out.println(String.format("Credit limit has been set successfully! New value is {%s}", creditLimit));
                nextHandler.handle(creditLimit);
                break;
            }
            System.out.println("Try Again");
        }
    }
}
