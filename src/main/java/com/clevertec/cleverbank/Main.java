package com.clevertec.cleverbank;

import com.clevertec.cleverbank.db.DatabaseConnection;
import com.clevertec.cleverbank.menu.MainMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Добро пожаловать в консольное приложение Clever-Bank!");
            MainMenu menu = new MainMenu(connection);
            menu.pushMenu(menu);
            menu.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
