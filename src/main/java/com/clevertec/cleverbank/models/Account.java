package com.clevertec.cleverbank.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Сущность счета пользователя
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id; //ID счета
    private String accountNumber; // номер счета
    private BigDecimal balance; // баланс на счету
    private Long userId; // ID владельца счета
    private Long bankId; // ID банка
    private LocalDate openingDate; // дата открытия счета
    private Currency currency; // валюта счета
}

