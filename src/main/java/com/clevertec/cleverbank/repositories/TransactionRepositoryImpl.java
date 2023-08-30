package com.clevertec.cleverbank.repositories;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.models.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса TransactionRepository для работы с транзакциями в базе данных.
 */
public class TransactionRepositoryImpl implements TransactionRepository {
    private final Connection connection;

    public TransactionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (sender_account_id, receiver_account_id, amount, time, transaction_type) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, transaction.getSenderAccountId());
            statement.setLong(2, transaction.getReceiverAccountId());
            statement.setBigDecimal(3, transaction.getAmount());
            statement.setTimestamp(4, Timestamp.valueOf(transaction.getTime()));
            statement.setObject(5, transaction.getType(), Types.OTHER);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                transaction.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getTransactionById(long transactionId) {
        String sql = "SELECT id, sender_account_id, receiver_account_id, amount, time, transaction_type " +
                "FROM transactions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, transactionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong("id"));
                transaction.setSenderAccountId(resultSet.getLong("sender_account_id"));
                transaction.setReceiverAccountId(resultSet.getLong("receiver_account_id"));
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setTime(resultSet.getTimestamp("time").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Если транзакция с указанным ID не найдена
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, sender_account_id, receiver_account_id, amount, time, transaction_type FROM transactions";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong("id"));
                transaction.setSenderAccountId(resultSet.getLong("sender_account_id"));
                transaction.setReceiverAccountId(resultSet.getLong("receiver_account_id"));
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setTime(resultSet.getTimestamp("time").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transactions SET sender_account_id = ?, receiver_account_id = ?, " +
                "amount = ?, time = ?, transaction_type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, transaction.getSenderAccountId());
            statement.setLong(2, transaction.getReceiverAccountId());
            statement.setBigDecimal(3, transaction.getAmount());
            statement.setLong(4, transaction.getId());
            statement.setTimestamp(4, Timestamp.valueOf(transaction.getTime()));
            statement.setObject(5, transaction.getType(), Types.OTHER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(long transactionId) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, transactionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
