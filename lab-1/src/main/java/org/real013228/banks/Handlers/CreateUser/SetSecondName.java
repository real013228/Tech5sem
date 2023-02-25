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
public class SetSecondName implements SetUserHandler {
    private Scanner scanner;

    public SetSecondName(CentralBank centralBank, Bank bank, Client.ClientBuilder builder, Scanner scanner) {
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
        builder.withLastName(value);
        var client = centralBank.registerClient(builder, bank);
        System.out.println("Your client's id: " + client.getId());
    }
}
