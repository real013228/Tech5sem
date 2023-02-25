package org.real013228.banks.Handlers.CreateBankAccount;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.CreateBankAccount;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankAccountParameter;

import java.util.Scanner;

public class SetAccountHandler implements SetBankAccountParameter {
    private Scanner scanner;
    private SetBankAccountParameter nextHandler;
    @Getter
    private Bank bank;
    @Getter
    private CreateBankAccount createBankAccount;

    @Override
    public void setNextHandler(SetBankAccountParameter nextHandler) {
        this.nextHandler = nextHandler;
    }

    public SetAccountHandler(Bank bank, CreateBankAccount createBankAccount, Scanner scanner) {
        this.scanner = scanner;
        this.bank = bank;
        this.createBankAccount = createBankAccount;
    }

    @Override
    public void handle(String value) throws ClientException {
        createBankAccount.setAccount(Double.parseDouble(value));
        createBankAccount.setBank(bank);
        bank.createBankAccount(createBankAccount);
    }
}
