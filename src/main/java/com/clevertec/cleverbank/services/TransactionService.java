package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.Transaction;
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
    Transaction createTransaction(TransactionType type, Long senderAccountId, Long receiverAccountId, BigDecimal amount);
}
