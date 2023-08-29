package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.TransactionType;

import java.math.BigDecimal;

/**
 * Интерфейс для работы с транзакциями.
 */
public interface TransactionService {

    /**
     * Создает новую транзакцию между аккаунтами.
     *
     * @param type Тип транзакции (перевод, пополнение, списание).
     * @param senderAccountId id аккаунта-отправителя.
     * @param receiverAccountId id аккаунта-получателя.
     * @param amount Сумма транзакции.
     */
    void createTransaction(TransactionType type, long senderAccountId, long receiverAccountId, BigDecimal amount);
}
