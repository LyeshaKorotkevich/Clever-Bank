package com.clevertec.cleverbank.account;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account);
    Account getAccountById(long accountId);
    List<Account> getAllAccounts();
    void updateAccount(Account account);
    void deleteAccount(long accountId);
}
