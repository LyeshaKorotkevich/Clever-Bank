package com.clevertec.cleverbank.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Сущность счета пользователя
 */

@Data
@AllArgsConstructor
public class Account {
    private Long id; //ID счета
    private String accountNumber; // номер счета
    private BigDecimal balance; // баланс на счету
    private Long userId; // ID владельца счета
    private Long bankId; // ID банка
    private LocalDate openingDate; // дата открытия счета
    private Currency currency; // валюта счета

    private static AtomicLong accountCounter = new AtomicLong(0L);

    public Account() {
        this.accountNumber = generateAccountNumber();
        this.balance = BigDecimal.valueOf(0);
        this.openingDate = LocalDate.now();
    }

    public Account(Long userId, Long bankId, Currency currency) {
        this.userId = userId;
        this.bankId = bankId;
        this.currency = currency;
        this.accountNumber = generateAccountNumber();
        this.balance = BigDecimal.valueOf(0);
        this.openingDate = LocalDate.now();
    }

    private String generateAccountNumber() {
        long accountNumberValue = accountCounter.incrementAndGet();
        return String.format("%010d", accountNumberValue);
    }
}

