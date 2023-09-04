package com.clevertec.cleverbank.services;

import java.math.BigDecimal;

/**
 * Интерфейс для работы с аккаунтами.
 */
public interface AccountService {

    /**
     * Пополняет баланс аккаунта.
     *
     * @param accountId id аккаунта, на который будет пополнение.
     * @param amount    Сумма для пополнения.
     */
    void deposit(Long accountId, BigDecimal amount);

    /**
     * Снимает средства с баланса аккаунта.
     *
     * @param accountId id аккаунта, с которого будет списание.
     * @param amount    Сумма для списания.
     */
    void withdraw(Long accountId, BigDecimal amount);

    /**
     * Переводит средства между аккаунтами.
     *
     * @param senderAccountId   id аккаунта-отправителя.
     * @param receiverAccountId id аккаунта-получателя.
     * @param amount            Сумма для перевода.
     */
    void transfer(Long senderAccountId, Long receiverAccountId, BigDecimal amount);
}
