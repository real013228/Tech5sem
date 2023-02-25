package org.real013228.banks.Handlers.CreateUser;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Handlers.HandlerAbstractions.SetUserHandler;

import java.io.Console;
import java.util.Scanner;

@Getter
public class SetFirstName implements SetUserHandler {
    private Scanner scanner;

    public SetFirstName(CentralBank centralBank, Bank bank, Client.ClientBuilder builder, Scanner scanner) {
        this.scanner = scanner;
        this.centralBank = centralBank;
        this.bank = bank;
        this.builder = builder;
    }

    private SetUserHandler nextHandler;
    private CentralBank centralBank;
    private Bank bank;
    private Client.ClientBuilder builder;
    @Override
    public void setNextHandler(SetUserHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(String value) throws ClientException, BankException {
        builder.withFirstName(value);
        while (true) {
            System.out.println("Please set your second name");
            String secondName = scanner.nextLine();
            if (secondName != null) {
                System.out.println("Second name has been set successfully! New value is " + secondName);
                nextHandler.handle(secondName);
                break;
            }

            System.out.println("Try again");
        }
    }
}
