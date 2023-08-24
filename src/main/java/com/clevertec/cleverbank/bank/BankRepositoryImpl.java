package com.clevertec.cleverbank.bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository{
    private final Connection connection;

    public BankRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createBank(Bank bank) {
        String sql = "INSERT INTO banks (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bank.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bank getBankById(long bankId) {
        String sql = "SELECT id, name FROM banks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, bankId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Bank.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Если банк с указанным ID не найден
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT id, name FROM banks";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Bank bank = Bank.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                banks.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }

    @Override
    public void updateBank(Bank bank) {
        String sql = "UPDATE banks SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bank.getName());
            statement.setLong(2, bank.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBank(long bankId) {
        String sql = "DELETE FROM banks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, bankId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

