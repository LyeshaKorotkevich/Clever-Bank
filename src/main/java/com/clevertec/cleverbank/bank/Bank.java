package com.clevertec.cleverbank.bank;

import lombok.*;

/**
 *  Сущность банка
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bank {
    private Long id; // ID банка
    private String name; // название банка
}

