package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.Account;

import java.util.List;

/**
 * Интерфейс для работы с аккаунтами в базе данных.
 */
public interface AccountRepository {

    /**
     * Создает новый аккаунт.
     *
     * @param account Данные нового аккаунта.
     */
    void createAccount(Account account);

    /**
     * Получает аккаунт по его id.
     *
     * @param accountId id аккаунта.
     * @return Объект Account, представляющий найденный аккаунт, или null, если аккаунт не найден.
     */
    Account getAccountById(long accountId);

    /**
     * Получает список всех аккаунтов.
     *
     * @return Список всех аккаунтов.
     */
    List<Account> getAllAccounts();

    /**
     * Обновляет информацию об аккаунте.
     *
     * @param account Объект Account с обновленными данными.
     */
    void updateAccount(Account account);

    /**
     * Удаляет аккаунт по его id.
     *
     * @param accountId id аккаунта для удаления.
     */
    void deleteAccount(long accountId);
}
