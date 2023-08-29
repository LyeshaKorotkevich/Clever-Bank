package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.User;

import java.util.List;

/**
 * Интерфейс определяет операции для работы с пользователями в базе данных.
 */
public interface UserRepository {

    /**
     * Создает нового пользователя.
     *
     * @param user Данные нового пользователя.
     */
    void createUser(User user);

    /**
     * Получает пользователя по его id.
     *
     * @param userId id пользователя.
     * @return Объект User, представляющий найденного пользователя, или null, если пользователь не найден.
     */
    User getUserById(long userId);

    /**
     * Получает список всех пользователей.
     *
     * @return Список всех пользователей.
     */
    List<User> getAllUsers();

    /**
     * Обновляет информацию о пользователе.
     *
     * @param user Объект User с обновленными данными.
     */
    void updateUser(User user);

    /**
     * Удаляет пользователя по его id.
     *
     * @param userId Идентификатор id для удаления.
     */
    void deleteUser(long userId);
}
