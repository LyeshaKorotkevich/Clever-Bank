package com.clevertec.cleverbank.account;

import com.clevertec.cleverbank.currency.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private final Connection connection;

    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, balance, user_id, bank_id, opening_date, currency) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getAccountNumber());
            statement.setBigDecimal(2, account.getBalance());
            statement.setLong(3, account.getUserId());
            statement.setLong(4, account.getBankId());
            statement.setDate(5, Date.valueOf(account.getOpeningDate()));
            statement.setString(6, account.getCurrency().toString());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccountById(long accountId) {
        String sql = "SELECT id, account_number, balance, user_id, bank_id, opening_date, currency " +
                "FROM accounts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setUserId(resultSet.getLong("user_id"));
                account.setBankId(resultSet.getLong("bank_id"));
                account.setOpeningDate(resultSet.getDate("opening_date").toLocalDate());
                account.setCurrency(Currency.valueOf(resultSet.getString("currency")));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Если счет с указанным ID не найден
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT id, account_number, balance, user_id, bank_id, opening_date, currency FROM accounts";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setUserId(resultSet.getLong("user_id"));
                account.setBankId(resultSet.getLong("bank_id"));
                account.setOpeningDate(resultSet.getDate("opening_date").toLocalDate());
                account.setCurrency(Currency.valueOf(resultSet.getString("currency")));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "UPDATE accounts SET account_number = ?, balance = ?, user_id = ?, bank_id = ?, " +
                "opening_date = ?, currency = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getAccountNumber());
            statement.setBigDecimal(2, account.getBalance());
            statement.setLong(3, account.getUserId());
            statement.setLong(4, account.getBankId());
            statement.setDate(5, Date.valueOf(account.getOpeningDate()));
            statement.setString(6, account.getCurrency().toString());
            statement.setLong(7, account.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(long accountId) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

