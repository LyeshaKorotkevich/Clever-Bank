package com.clevertec.cleverbank.models;

import lombok.*;

/**
 * Сущность пользователя(клиента)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;// ID пользователя
    private String fullName; // ФИО пользователя
}

