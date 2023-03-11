package org.real013228.banks.Domain.Entities;

import org.real013228.banks.Domain.Abstractions.BankAccount;
import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Abstractions.Transaction;
import org.real013228.banks.Domain.CustomExceptions.*;
import org.real013228.banks.Domain.Entities.Bank.BankBuilder;
import org.real013228.banks.Domain.Entities.Actions.BankAccAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/***
 * Central bank implementation of CentralBank interface
 */
public class CentralBankImplementation implements CentralBank {
    private final List<Bank> banks;
    private final List<BankAccount> accounts;
    private final List<TransactionWrapper> transactions;
    private final List<Client> clients;
    public CentralBankImplementation() {
        banks = new ArrayList<>();
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
        clients = new ArrayList<>();
    }

    @Override
    public Bank getBankFromId(UUID id) throws BankException {
        Bank bank = banks.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (bank == null)
            throw BankException.invalidBankId(id);
        return bank;
    }

    @Override
    public Bank createBank(BankBuilder builder) {
        Bank bank = builder.build();
        banks.add(bank);
        var newBankAction = new BankAccAction(this::accountCreated);
        bank.getAccountCreatedSubscribers().addSubscriber(newBankAction);
        return bank;
    }

    @Override
    public Client registerClient(Client.ClientBuilder clientBuilder, Bank bank) throws ClientException, BankException {
        Client client = clientBuilder
                .build();

        bank.registerClient(client);
        clients.add(client);
        return client;
    }

    @Override
    public void makeTransaction(Transaction transaction) throws TransactionException, AccountException, BalanceException {
        var visitor = new TransactionVisitor(accounts);
        transaction.accept(visitor);
        transactions.add(visitor.getTransaction());
    }

    @Override
    public void cancelTransaction(UUID transactionId) throws TransactionException, BalanceException {
        TransactionWrapper transactionWrapper = transactions.stream().filter(x -> x.getTransaction().getId() == transactionId).findFirst().orElse(null);
        if (transactionWrapper == null)
            throw TransactionException.nullAccountException();
        transactionWrapper.cancelTransaction();
        transactions.remove(transactionWrapper);
    }
    private void accountCreated(BankAccount account) {
        accounts.add(account);
    }
}
