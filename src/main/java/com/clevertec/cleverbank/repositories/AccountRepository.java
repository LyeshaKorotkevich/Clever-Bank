package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.Account;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account);
    Account getAccountById(long accountId);
    List<Account> getAllAccounts();
    void updateAccount(Account account);
    void deleteAccount(long accountId);
}
