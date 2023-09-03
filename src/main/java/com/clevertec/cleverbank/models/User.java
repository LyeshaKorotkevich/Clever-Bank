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
    private String surname; // фамилия пользователя
    private String name; // имя пользователя
    private String middleName; // отчество пользователя

    public String getFullName(){
        return surname + " " + name + " " + middleName;
    }
}

