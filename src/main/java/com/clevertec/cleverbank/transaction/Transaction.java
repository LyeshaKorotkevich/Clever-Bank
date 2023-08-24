package com.clevertec.cleverbank.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность транзакции
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id; // ID транзакции
    private LocalDateTime time; // время транзакции
    private Long senderAccountId; // ID счета отправителя
    private Long receiverAccountId; // ID счета получателя
    private BigDecimal amount; // сумма транзакции
    private TransactionType type; // вид транзакции
}
