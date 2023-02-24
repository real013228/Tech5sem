package org.real013228.banks.Domain.Models;

import org.real013228.banks.Domain.Abstractions.BankAccount;

import java.util.List;

public class AccountCollection {
    private final List<BankAccount> accounts;

    public AccountCollection(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    /***
     * method, that checks for the existence of an account
     * @param account bank account
     * @return true, if there is an account, otherwise returns false
     */
    public boolean contains(BankAccount account) {
        return accounts.contains(account);
    }
}
