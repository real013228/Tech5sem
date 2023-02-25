package org.real013228.banks.Handlers.CreateUser;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerAbstractions.SetUserHandler;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

import java.util.Scanner;

@Getter
public class CreateUserHandler implements ConsoleApplicationHandler {
    private ConsoleApplicationHandler nextHandler;
    private SetUserHandler handler;
    private Scanner scanner;
    private Bank bank;
    private CentralBank mainCentralBank;

    public CreateUserHandler(Scanner scanner, Bank bank, CentralBank mainCentralBank) {
        this.handler = handler;
        this.scanner = scanner;
        this.bank = bank;
        this.mainCentralBank = mainCentralBank;
    }

    @Override
    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler) {
        this.handler = (SetUserHandler) handler;
    }

    @Override
    public void setNextHandler(ConsoleApplicationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(char key) throws TransactionException, BalanceException, ClientException, BankException {
        if (key == '2') {
            System.out.println("\nDo you want to make registration as a client?\ny/n");
            if (!scanner.nextLine().equals("y")) return;
            Client.ClientBuilder builder = Client.builder();
            var firstNameHandler = new SetFirstName(mainCentralBank, bank, builder, scanner);
            var secondNameHandler = new SetSecondName(mainCentralBank, bank, builder, scanner);
            setLessResponsibilitiesHandler(firstNameHandler);
            firstNameHandler.setNextHandler(secondNameHandler);
            System.out.println("\nPlease enter your first name");
            while (true) {
                String firstName = scanner.nextLine();
                if (firstName != null) {
                    System.out.println("Your first name has been set successfully! New value is " + firstName);
                    handler.handle(firstName);
                    System.out.println("You have passed a registration successfully!");
                    break;
                }

                System.out.println("Please try again");
            }
        } else {
            nextHandler.handle(key);
        }
    }
}