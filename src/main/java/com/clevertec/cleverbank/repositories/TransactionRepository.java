package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.Transaction;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction transaction);
    Transaction getTransactionById(long transactionId);
    List<Transaction> getAllTransactions();
    void updateTransaction(Transaction transaction);
    void deleteTransaction(long transactionId);
}
