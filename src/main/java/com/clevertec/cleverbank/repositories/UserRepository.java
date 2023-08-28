package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);
    User getUserById(long userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long userId);
}
