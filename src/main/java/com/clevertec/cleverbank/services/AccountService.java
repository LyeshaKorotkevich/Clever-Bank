package com.clevertec.cleverbank.services;

import java.math.BigDecimal;

public interface AccountService {
    void deposit(Long accountId, BigDecimal amount);
    void withdraw(Long accountId, BigDecimal amount);
    void transfer(long senderAccountId, long receiverAccountId, BigDecimal amount);
}

