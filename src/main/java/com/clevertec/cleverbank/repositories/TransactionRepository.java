package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.util.TimePeriod;

import java.util.List;

/**
 * Интерфейс определяет операции для работы с транзакциями в базе данных.
 */
public interface TransactionRepository {

    /**
     * Создает новую транзакцию.
     *
     * @param transaction Данные новой транзакции.
     */
    void createTransaction(Transaction transaction);

    /**
     * Получает транзакцию по её id.
     *
     * @param transactionId id транзакции.
     * @return Объект Transaction, представляющий найденную транзакцию, или null, если транзакция не найдена.
     */
    Transaction getTransactionById(long transactionId);

    /**
     * Получает список транзакций пользователя.
     *
     * @return Список транзакций.
     */
    List<Transaction> getTransactionsByUser(long userId);

    /**
     * Получает список транзакций пользователя за определенный период.
     *
     * @return Список транзакций.
     */
    List<Transaction> getTransactionsByPeriod(long userId, TimePeriod period);

    /**
     * Получает список всех транзакций.
     *
     * @return Список всех транзакций.
     */
    List<Transaction> getAllTransactions();

    /**
     * Обновляет информацию о транзакции.
     *
     * @param transaction Объект Transaction с обновленными данными.
     */
    void updateTransaction(Transaction transaction);

    /**
     * Удаляет транзакцию по её id.
     *
     * @param transactionId id транзакции для удаления.
     */
    void deleteTransaction(long transactionId);
}