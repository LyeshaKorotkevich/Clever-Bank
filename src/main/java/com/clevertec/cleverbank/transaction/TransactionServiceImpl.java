package com.clevertec.cleverbank.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepositoryImpl transactionRepository;

    public TransactionServiceImpl(TransactionRepositoryImpl transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(TransactionType type, long senderAccountId, long receiverAccountId, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTime(LocalDateTime.now());
        transaction.setType(type);

        if (type == TransactionType.DEPOSIT  || type == TransactionType.WITHDRAW) {
            transaction.setReceiverAccountId(receiverAccountId);
        } else if (type == TransactionType.TRANSFER) {
            transaction.setSenderAccountId(senderAccountId);
            transaction.setReceiverAccountId(receiverAccountId);
        }

        transaction.setAmount(amount);

        transactionRepository.createTransaction(transaction);

        return transaction;
    }
}