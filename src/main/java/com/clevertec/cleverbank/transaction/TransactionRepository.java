package com.clevertec.cleverbank.transaction;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction transaction);
    Transaction getTransactionById(long transactionId);
    List<Transaction> getAllTransactions();
    void updateTransaction(Transaction transaction);
    void deleteTransaction(long transactionId);
}
