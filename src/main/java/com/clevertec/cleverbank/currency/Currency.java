package com.clevertec.cleverbank.currency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление с поддерживаемыми валютами
 */

@Getter
@RequiredArgsConstructor
public enum Currency {
    /**
     * Белорусский рубль (BYN).
     */
    BYN("BYN"),

    /**
     * Доллар США (USD).
     */
    USD("USD"),

    /**
     * Евро (EUR).
     */
    EUR("EUR"),

    /**
     * Российский рубль (RUB).
     */
    RUB("RUB");

    private final String code;
}
