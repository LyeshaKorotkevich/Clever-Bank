package com.clevertec.cleverbank.account;

import com.clevertec.cleverbank.currency.Currency;
import lombok.*;

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
    private double balance; // баланс на счету
    private Long userId; // ID владельца счета
    private Long bankId; // ID банка
    private LocalDate openingDate; // дата открытия счета
    private Currency currency; // валюта счета


}

