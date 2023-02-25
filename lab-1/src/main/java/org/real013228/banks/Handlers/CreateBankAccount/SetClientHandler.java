package org.real013228.banks.Handlers.CreateBankAccount;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Abstractions.CreateBankAccount;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankAccountParameter;

import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class SetClientHandler implements SetBankAccountParameter {
    private Scanner scanner;

    public SetClientHandler(Scanner scanner, Bank bank, CreateBankAccount createBankAccount) {
        this.scanner = scanner;
        this.bank = bank;
        this.createBankAccount = createBankAccount;
    }

    private SetBankAccountParameter nextHandler;
    @Getter
    private Bank bank;
    @Getter
    private CreateBankAccount createBankAccount;

    @Override
    public void setNextHandler(SetBankAccountParameter nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(String value) throws ClientException, BankException {
        System.out.println("\nPlease set the start money to your credit account");
        createBankAccount.setClient(bank.getClientById(UUID.fromString(value)));
        while (true)
        {
            Double accountValue = Double.parseDouble(scanner.nextLine());
            if (accountValue != null)
            {
                System.out.println("Start account has been set successfully");
                nextHandler.handle(accountValue.toString());
                break;
            }

            System.out.println("Try Again");
        }
    }
}
