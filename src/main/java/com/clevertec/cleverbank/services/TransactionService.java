package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.models.TransactionType;

import java.math.BigDecimal;

public interface TransactionService {
    Transaction createTransaction(TransactionType type, long senderAccountId, long receiverAccountId, BigDecimal amount);
}
