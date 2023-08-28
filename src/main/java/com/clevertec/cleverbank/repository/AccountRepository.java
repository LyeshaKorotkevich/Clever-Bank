package com.clevertec.cleverbank.repository;

import com.clevertec.cleverbank.model.Account;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account);
    Account getAccountById(long accountId);
    List<Account> getAllAccounts();
    void updateAccount(Account account);
    void deleteAccount(long accountId);
}
