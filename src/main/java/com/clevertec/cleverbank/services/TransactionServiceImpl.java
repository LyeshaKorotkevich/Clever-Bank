package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.models.TransactionType;
import com.clevertec.cleverbank.repositories.TransactionRepositoryImpl;

import java.math.BigDecimal;

/**
 * Реализация интерфейса TransactionService для создания транзакций между аккаунтами.
 */
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepositoryImpl transactionRepository;

    public TransactionServiceImpl(TransactionRepositoryImpl transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(TransactionType type, Long senderAccountId, Long receiverAccountId, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setType(type);

        if (type == TransactionType.DEPOSIT  || type == TransactionType.WITHDRAW) {
            transaction.setReceiverAccountId(receiverAccountId);
            transaction.setSenderAccountId(senderAccountId);
        } else if (type == TransactionType.TRANSFER) {
            transaction.setSenderAccountId(senderAccountId);
            transaction.setReceiverAccountId(receiverAccountId);
        }

        transaction.setAmount(amount);

        transactionRepository.createTransaction(transaction);

        return transaction;
    }
}