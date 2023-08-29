package com.clevertec.cleverbank;

import com.clevertec.cleverbank.db.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if(connection != null){

        }
        DatabaseConnection.closeConnection(connection);
    }
}
