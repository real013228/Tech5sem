package org.real013228.banks.Handlers.CreateBankAccount;

import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.NotifyStrategy;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankAccountParameter;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

import java.util.Scanner;

public class CreateBankAccountHandler implements ConsoleApplicationHandler {
    private final Bank bank;
    private final Clock clock;
    private final NotifyStrategy strategy;
    private ConsoleApplicationHandler nextHandler;
    private SetBankAccountParameter handler;
    private Scanner scanner;

    public CreateBankAccountHandler(Bank bank, Clock clock, NotifyStrategy strategy, CentralBank mainCentralBank, Scanner scanner) {
        this.bank = bank;
        this.clock = clock;
        this.strategy = strategy;
        this.mainCentralBank = mainCentralBank;
        this.scanner = scanner;
    }

    private final CentralBank mainCentralBank;
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
        if (key == '3')
        {
            System.out.println("\nDo you want to create a bank account?\ny/n");
            if (!scanner.nextLine().equals("y")) return;
            var creditAccount = new CreateCreditAccountHandler(clock, strategy, bank, scanner, mainCentralBank);
            var debitAccount = new CreateDebitAccountHandler(mainCentralBank, bank, strategy, clock, scanner);
            var depositAccount = new CreateCreditAccountHandler(clock, strategy, bank, scanner, mainCentralBank);
            creditAccount.setNextHandler(debitAccount);
            debitAccount.setNextHandler(depositAccount);
            while (true)
            {
                System.out.println("\nChoose a number as a kind of account you want to create");
                System.out.println("1 - Credit account");
                System.out.println("2 - Debit account");
                System.out.println("3 - Deposit account");
                System.out.println("q - Quit");
                String newKey = scanner.nextLine();
                if (newKey.equals("1") || newKey.equals("2") || newKey.equals("3"))
                {
                    creditAccount.handle(newKey.charAt(0));
                } else if  (newKey.equals("q")) {
                    break;
                }
            }
        }
        else
        {
            nextHandler.handle(key);
        }
    }
}
