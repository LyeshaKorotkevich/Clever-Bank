package com.clevertec.cleverbank;

import com.clevertec.cleverbank.db.DatabaseConnection;
import com.clevertec.cleverbank.util.ConfigReader;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConfigReader.getInterestRate());
//        Connection connection = DatabaseConnection.getConnection();
//        if(connection != null){
//
//        }
//        DatabaseConnection.closeConnection(connection);
    }
}
