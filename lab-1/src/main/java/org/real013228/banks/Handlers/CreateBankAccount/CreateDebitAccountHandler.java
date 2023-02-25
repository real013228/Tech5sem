package org.real013228.banks.Handlers.CreateBankAccount;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.AccountCreators.CreateDebitAccount;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankAccountParameter;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

import java.util.Scanner;

public class CreateDebitAccountHandler implements ConsoleApplicationHandler {
    private ConsoleApplicationHandler nextHandler;
    private SetBankAccountParameter handler;
    private Bank bank;
    private Clock clock;
    private NotifyStrategy strategy;
    private Scanner scanner;

    public CreateDebitAccountHandler(CentralBank mainCentralBank, Bank bank, NotifyStrategy strategy, Clock clock, Scanner scanner)
    {
        this.mainCentralBank = mainCentralBank;
        this.bank = bank;
        this.strategy = strategy;
        this.clock = clock;
        this.scanner = scanner;
    }
    @Getter
    private CentralBank mainCentralBank;

    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler)
    {
        this.handler =  (SetBankAccountParameter) handler;
    }

    public void setNextHandler(ConsoleApplicationHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }

    public void handle(char key) throws ClientException, TransactionException, BalanceException, BankException {
        if (key == '2')
        {
            var creator = new CreateDebitAccount(clock, strategy);
            var clientHandler = new SetClientHandler(scanner, bank, creator);
            var accountHandler = new SetAccountHandler(bank, creator, scanner);
            clientHandler.setNextHandler(accountHandler);
            setLessResponsibilitiesHandler(clientHandler);
            while (true)
            {
                System.out.println("Please set a client Id");
                String clientName = scanner.nextLine();
                if (clientName != null)
                {
                    System.out.println("User name has been set successfully! Value is - {clientName}");
                    handler.handle(clientName);
                    System.out.println("Debit account has been created successfully!");
                    break;
                }

                System.out.println("Try again at next time");
                break;
            }
        }
        else
        {
            nextHandler.handle(key);
        }
    }
}
