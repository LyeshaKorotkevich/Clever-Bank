package com.clevertec.cleverbank.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
