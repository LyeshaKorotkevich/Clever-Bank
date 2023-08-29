package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.Bank;

import java.util.List;

/**
 * Интерфейс определяет операции для работы с банками в базе данных.
 */
public interface BankRepository {

    /**
     * Создает новый банк.
     *
     * @param bank Данные нового банка.
     */
    void createBank(Bank bank);

    /**
     * Получает банк по его id.
     *
     * @param bankId id банка.
     * @return Объект Bank, представляющий найденный банк, или null, если банк не найден.
     */
    Bank getBankById(long bankId);

    /**
     * Получает список всех банков.
     *
     * @return Список всех банков.
     */
    List<Bank> getAllBanks();

    /**
     * Обновляет информацию о банке.
     *
     * @param bank Объект Bank с обновленными данными.
     */
    void updateBank(Bank bank);

    /**
     * Удаляет банк по его id.
     *
     * @param bankId id банка для удаления.
     */
    void deleteBank(long bankId);
}
