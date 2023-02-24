package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.CustomExceptions.*;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;

import java.util.UUID;

/***
 * central bank interface
 */
public interface CentralBank {
    /***
     * bank getter method
     * @param id id of the bank
     * @return bank by id
     */
    Bank getBankFromId(UUID id) throws BankException;

    /***
     * bank builder method
     * @param builder builder, that should build the bank
     * @return new bank
     */
    Bank createBank(Bank.BankBuilder builder);

    /***
     * registering client method
     * @param clientBuilder builder, that should build the client
     * @param bank bank, where client should be registered
     * @return new client
     */
    Client registerClient(Client.ClientBuilder clientBuilder, Bank bank) throws ClientException, BankException;

    /***
     * making transaction method
     * @param transaction transaction that should be done
     */
    void makeTransaction(Transaction transaction) throws TransactionException, AccountException, BalanceException;

    /***
     * cancelling transaction method
     * @param transaction transaction that should be cancelled
     */
    void cancelTransaction(UUID transaction) throws TransactionException, BalanceException;
}
