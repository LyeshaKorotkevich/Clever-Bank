package com.clevertec.cleverbank.repository;

import com.clevertec.cleverbank.model.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);
    User getUserById(long userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long userId);
}
