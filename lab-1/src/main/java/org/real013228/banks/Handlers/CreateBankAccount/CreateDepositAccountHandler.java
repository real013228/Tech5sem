package org.real013228.banks.Handlers.CreateBankAccount;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.AccountCreators.CreateCreditAccount;
import org.real013228.banks.Domain.Entities.AccountCreators.CreateDepositAccount;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Models.DepositHandlers.ConcreteDepositHandler;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankAccountParameter;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

import java.util.Scanner;

public class CreateDepositAccountHandler implements ConsoleApplicationHandler {
    private ConsoleApplicationHandler nextHandler;
    private SetBankAccountParameter handler;
    private Clock clock;
    private NotifyStrategy strategy;
    private Bank bank;
    private Scanner scanner;
    private CentralBank mainCentralBank;

    public CreateDepositAccountHandler(Clock clock, NotifyStrategy strategy, Bank bank, Scanner scanner, CentralBank mainCentralBank) {
        this.clock = clock;
        this.strategy = strategy;
        this.bank = bank;
        this.scanner = scanner;
        this.mainCentralBank = mainCentralBank;
    }

    @Override
    public CentralBank getMainCentralBank() {
        return mainCentralBank;
    }

    @Override
    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler) {
        this.handler = (SetBankAccountParameter) handler;
    }

    @Override
    public void setNextHandler(ConsoleApplicationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(char key) throws TransactionException, BalanceException, ClientException, BankException {
        if (key == '3') {
            var depositCalculator1 = new ConcreteDepositHandler(3, 0, 50000);
            var depositCalculator2 = new ConcreteDepositHandler(3.5d, 50000, 100000);
            var depositCalculator3 = new ConcreteDepositHandler(4, 50000, 100000);
            depositCalculator1.setNextHandler(depositCalculator2);
            depositCalculator2.setNextHandler(depositCalculator3);
            var creator = new CreateDepositAccount(clock, depositCalculator1, strategy);
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
                    System.out.println("User name has been set successfully! Value is " + clientName);
                    handler.handle(clientName);
                    System.out.println("Deposit account has been created successfully!");
                    break;
                }

                System.out.println("Try again at next time");
                break;
            }
        }
    }
}
