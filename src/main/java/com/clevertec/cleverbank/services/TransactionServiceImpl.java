package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.models.TransactionType;
import com.clevertec.cleverbank.repositories.TransactionRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Реализация интерфейса TransactionService для создания транзакций между аккаунтами.
 */
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepositoryImpl transactionRepository;

    public TransactionServiceImpl(TransactionRepositoryImpl transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(TransactionType type, long senderAccountId, long receiverAccountId, BigDecimal amount) {
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
    }
}