package com.clevertec.cleverbank.account;

import java.math.BigDecimal;

public interface AccountService {
    void deposit(Long accountId, BigDecimal amount);
    void withdraw(Long accountId, BigDecimal amount);
    void transfer(long sourceAccountId, long targetAccountId, double amount);
}

