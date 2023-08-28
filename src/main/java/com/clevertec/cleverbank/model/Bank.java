package com.clevertec.cleverbank.model;

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

