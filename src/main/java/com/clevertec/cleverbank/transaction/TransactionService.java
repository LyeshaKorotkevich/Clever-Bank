package com.clevertec.cleverbank.transaction;

import java.math.BigDecimal;

public interface TransactionService {
    Transaction createTransaction(TransactionType type, long senderAccountId, long receiverAccountId, BigDecimal amount);
}
