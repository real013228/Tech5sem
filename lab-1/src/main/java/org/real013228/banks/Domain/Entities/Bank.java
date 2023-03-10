package org.real013228.banks.Domain.Entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.Clock;
import org.real013228.banks.Domain.Abstractions.CreateBankAccount;
import org.real013228.banks.Domain.Abstractions.DepositCalculator;
import org.real013228.banks.Domain.CustomExceptions.BankException;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Entities.Actions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/***
 * Bank entity
 */
@Getter
public class Bank {
    @Getter(AccessLevel.NONE)
    private final List<BankAccount> accounts;
    @Getter(AccessLevel.NONE)
    private final List<Client> clients;
    private double creditLimit;
    private final double commission;
    private double transactionLimit;
    private double debitPercent;
    private int expirationDays;
    private Clock clock;
    private final UUID id;
    private final Actions accountCreatedSubscribers;
    private final Actions commissionHasBeenChangedSubscribers;
    private final Actions creditLimitHasBeenChangedSubscribers;
    private final Actions transactionLimitHasBeenChanged;
    private final Actions timeIntervalHasBeenChangedSubscribers;
    private final Actions debitPercentHasBeenChangedSubscribers;
    @Builder
    private Bank(double debitPercent, double commission, double creditLimit, double transactionLimit, int expirationDays, Clock clock) {
        this.debitPercent = debitPercent;
        this.accountCreatedSubscribers = new Actions();
        this.commissionHasBeenChangedSubscribers = new Actions();
        this.creditLimitHasBeenChangedSubscribers = new Actions();
        this.transactionLimitHasBeenChanged = new Actions();
        this.timeIntervalHasBeenChangedSubscribers = new Actions();
        this.debitPercentHasBeenChangedSubscribers = new Actions();
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.commission = commission;
        this.creditLimit = creditLimit;
        this.transactionLimit = transactionLimit;
        this.expirationDays = expirationDays;
        this.id = UUID.randomUUID();
        this.clock = clock;
    }
    public static BankBuilder builder() {
        return new BankBuilder();

    }
    public void setCreditLimit(double value) {
        creditLimit = value;
        creditLimitHasBeenChangedSubscribers.invoke(value);
    }
    public void setTransactionLimit(double value) {
        transactionLimit = value;
        transactionLimitHasBeenChanged.invoke(value);
    }
    public void setDebitPercent(double value) {
        debitPercent = value;
        debitPercentHasBeenChangedSubscribers.invoke(value);
    }
    public void setExpirationDays(int value) {
        expirationDays = value;
        timeIntervalHasBeenChangedSubscribers.invoke(value);
    }

    public UUID createBankAccount(CreateBankAccount creator) {
        BankAccount acc = creator.build();
        accounts.add(acc);
        accountCreatedSubscribers.invoke(acc);
        return acc.getId();
    }
    public double calculateDepositPercent(DepositCalculator calculator, double value) {
        return calculator.handleRequest(value);
    }
    public void registerClient(Client client) throws BankException {
        if (clients.stream().filter(x -> x.getId() == client.getId()).findFirst().orElse(null) != null) {
            throw BankException.clientAlreadyExist(client.getId());
        }
        clients.add(client);
    }
    public Client getClientById(UUID id) throws ClientException {
        Client client = clients.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if (client != null)
            return client;
        throw ClientException.cannotFindClientById(id);
    }
}