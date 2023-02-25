package org.real013228.banks.Domain.Abstractions;

import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.Client;

/***
 * Creating bank account interface
 */
public interface CreateBankAccount {
    /***
     * setting bank method
     * @param bank bank, that should be set
     */
    void setBank(Bank bank);

    /***
     * setting account method
     * @param account account, that should be set
     */
    void setAccount(double account);

    /***
     * setting client method
     * @param client client, that should be set
     */
    void setClient(Client client);

    /***
     * building method
     * @return new bank account
     */
    BankAccount build();
}
