package org.real013228.banks.Handlers.CreateBank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Handlers.HandlerAbstractions.SetBankParameter;

import java.util.Scanner;

public class SetTimeIntervalHandler implements SetBankParameter {
    private SetBankParameter nextHandler;
    private CentralBank mainCentralBank;
    private Scanner scanner;
    private Bank.BankBuilder builder;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    Bank bank;

    public SetTimeIntervalHandler(CentralBank mainCentralBank, Scanner scanner, Bank.BankBuilder builder) {
        this.mainCentralBank = mainCentralBank;
        this.scanner = scanner;
        this.builder = builder;
    }

    @Override
    public CentralBank getMainCentralBank() {
        return mainCentralBank;
    }

    @Override
    public Bank.BankBuilder getBuilder() {
        return builder;
    }

    @Override
    public void setNextHandler(SetBankParameter nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(String value) {
        builder.expirationDays(Integer.parseInt(value));
        bank = mainCentralBank.createBank(builder);
    }
}
