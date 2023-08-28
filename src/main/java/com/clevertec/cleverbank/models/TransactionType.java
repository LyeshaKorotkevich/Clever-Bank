package com.clevertec.cleverbank.models;

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
