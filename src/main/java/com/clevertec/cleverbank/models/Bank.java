package com.clevertec.cleverbank.models;

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

