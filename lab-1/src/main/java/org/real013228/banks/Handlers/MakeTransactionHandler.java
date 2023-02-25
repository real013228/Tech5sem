package org.real013228.banks.Handlers;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;

import java.util.Scanner;

public class MakeTransactionHandler implements ConsoleApplicationHandler {
    private Scanner scanner;
    private ConsoleApplicationHandler nextHandler;
    @Getter
    private CentralBank mainCentralBank;

    public MakeTransactionHandler(CentralBank mainCentralBank, Scanner scanner) {
        this.scanner = scanner;
        this.mainCentralBank = mainCentralBank;
    }

    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler) { }

    public void setNextHandler(ConsoleApplicationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(char key) throws TransactionException, BankException, BalanceException, ClientException {
        if (key == '4') {
            System.out.println("Do you want to make a transaction?\ny/n");
            if (!scanner.nextLine().equals("y")) return;
            System.out.println("\nChoose a number as a kind of transaction you want to make");
            System.out.println("1 - Increase money");
            System.out.println("2 - Decrease money");
            System.out.println("3 - Transfer money");
            String line = scanner.nextLine();
            switch (line.charAt(0)) {
                case '1' -> System.out.println("Nice choose in awesome bank, bro");
                case '2' -> System.out.println("Good transaction for great client, bro");
                case '3' -> System.out.println("Generosity is a well quality, bro");
                default -> System.out.println("Normalno vyberi ee russkim yazykov zhe napisano 1 2 3 vybiray");
            }
        } else {
            nextHandler.handle(key);
        }
    }
}
