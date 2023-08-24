package com.clevertec.cleverbank.transaction;

import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal amount; // сумма транзакции
}
