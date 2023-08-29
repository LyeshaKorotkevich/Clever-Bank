package com.clevertec.cleverbank.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление видов транзакций
 */
@Getter
@RequiredArgsConstructor
public enum TransactionType {
    /**
     * Пополнение
     */
    DEPOSIT("Пополнение"),

    /**
     * Снятие
     */
    WITHDRAW("Снятие"),

    /**
     * Перевод
     */
    TRANSFER("Перевод");

    private final String translate;
}
