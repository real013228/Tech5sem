package org.real013228.banks.Handlers.CreateBank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.CustomExceptions.BalanceException;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.CustomExceptions.TransactionException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.ConsoleApplicationHandler;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankParameter;
import org.real013228.banks.Handlers.HandlerLessResponsibilities;

import java.util.Scanner;

public class CreateBankHandler implements ConsoleApplicationHandler {
    private Scanner scanner;
    private ConsoleApplicationHandler nextHandler;
    @Getter
    private CentralBank mainCentralBank;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Bank.BankBuilder builder;
    private SetBankParameter handler;
    public CreateBankHandler(Scanner scanner, CentralBank centralBank) {
        this.scanner = scanner;
        mainCentralBank = centralBank;
    }

    @Override
    public void setLessResponsibilitiesHandler(HandlerLessResponsibilities handler) {
        this.handler = (SetBankParameter) handler;
    }

    @Override
    public void setNextHandler(ConsoleApplicationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(char key) throws TransactionException, BalanceException, BankException, ClientException {
        if (key == '1') {
            System.out.println("Do you want to create a bank?\ny/n");
            if (!scanner.nextLine().equals("y")) return;
            this.builder = Bank.builder();
            var commissionHandler = new SetCommissionHandler(mainCentralBank, builder,scanner);
            var creditLimitHandler = new SetCreditLimitHandler(mainCentralBank, scanner, builder);
            var debitPercentHandler = new SetDebitPercentHandler(mainCentralBank, scanner, builder);
            var transactionLimitHandler = new SetTransactionLimitHandler(mainCentralBank, scanner, builder);
            var timeIntervalHandler = new SetTimeIntervalHandler(mainCentralBank, scanner, builder);
            setLessResponsibilitiesHandler(commissionHandler);
            commissionHandler.setNextHandler(creditLimitHandler);
            creditLimitHandler.setNextHandler(debitPercentHandler);
            debitPercentHandler.setNextHandler(transactionLimitHandler);
            transactionLimitHandler.setNextHandler(timeIntervalHandler);
            System.out.println("Please enter a commission for new bank");
            while(true) {
                Double commission = Double.parseDouble(scanner.nextLine());
                if (commission != null) {
                    System.out.println(String.format("Commission has been set successfully! New value is %f", commission));
                    handler.handle(commission.toString());
                    System.out.println("The bank has been created successfully!");
                    break;
                }

                System.out.println("Try again");
            }
        } else {
            nextHandler.handle(key);
        }
    }
}
