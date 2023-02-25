package org.real013228.banks;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.CentralBankImplementation;
import org.real013228.banks.Domain.Entities.Client;
import org.real013228.banks.Domain.Models.ClockImplementation;
import org.real013228.banks.Handlers.CancelTransaction.CancelTransactionHandler;
import org.real013228.banks.Handlers.CreateBank.CreateBankHandler;
import org.real013228.banks.Handlers.CreateBankAccount.CreateBankAccountHandler;
import org.real013228.banks.Handlers.CreateUser.CreateUserHandler;
import org.real013228.banks.Handlers.MakeTransactionHandler;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws TransactionException, BalanceException, ClientException, BankException {
        var scanner = new Scanner(System.in);
        System.out.println("\nHey there! I am using WhatsApp. Do you want to create a central bank and a bank?\ny/n");
        while (scanner.nextLine().charAt(0) != 'y')
        {
            System.out.println("\nPlease enter \"y\"");
        }

        CentralBank centralBank = new CentralBankImplementation();
        System.out.println(" ");
        var bankCreator = new CreateBankHandler(scanner, centralBank);
        bankCreator.handle('1');
        Bank bankForAll = bankCreator.getBuilder().build();

        while (true)
        {
            MenuLog();
            String line = scanner.nextLine();
            char key = line.charAt(0);
            if (key == 'q')
            {
                System.out.println("See you soon!");
                break;
            }

            Client client = Client.builder()
                    .withFirstName("Natsuki")
                    .withLastName("Subaru")
                    .build();
//            var notifyStrategy = new NotifyStrategy(client, notifyStrategyAction);
            var clock = new ClockImplementation();
            var clientCreator = new CreateUserHandler(scanner, bankForAll, centralBank);
            var bankAccountCreator = new CreateBankAccountHandler(bankForAll, clock,null, centralBank, scanner);
            var makeTransactionCreator = new MakeTransactionHandler(centralBank, scanner);
            var cancelTransaction = new CancelTransactionHandler(scanner, centralBank);
            bankCreator.setNextHandler(clientCreator);
            clientCreator.setNextHandler(bankAccountCreator);
            bankAccountCreator.setNextHandler(makeTransactionCreator);
            makeTransactionCreator.setNextHandler(cancelTransaction);
            clientCreator.handle(key);
        }
    }

    private static void MenuLog()
    {
        System.out.println("2 - Create user");
        System.out.println("3 - Create bank account");
        System.out.println("4 - Make transaction");
        System.out.println("5 - Cancel transaction");
        System.out.println("q - Quit from menu");
    }
}