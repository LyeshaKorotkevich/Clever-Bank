package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.model.Transaction;
import com.clevertec.cleverbank.model.TransactionType;

import java.math.BigDecimal;

public interface TransactionService {
    Transaction createTransaction(TransactionType type, long senderAccountId, long receiverAccountId, BigDecimal amount);
}
