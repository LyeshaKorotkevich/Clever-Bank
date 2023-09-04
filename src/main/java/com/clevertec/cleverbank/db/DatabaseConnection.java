package com.clevertec.cleverbank.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для установления и закрытия соединения с базой данных.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5438/banks_db";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "root";

    /**
     * Получает соединение с базой данных.
     *
     * @return Объект Connection, представляющий соединение с базой данных.
     * @throws RuntimeException Если произошла ошибка при установлении соединения с базой данных.
     */
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Закрывает соединение с базой данных.
     *
     * @param connection Соединение для закрытия.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

