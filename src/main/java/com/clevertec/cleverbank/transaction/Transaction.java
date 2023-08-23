package com.clevertec.cleverbank.transaction;

import lombok.*;

/**
 * Сущность транзакции
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id; // ID транзакции
    private Long senderAccountId; // ID счета отправителя
    private Long receiverAccountId; // ID счета получателя
    private double amount; // сумма транзакции

}
