package com.clevertec.cleverbank.util;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.models.TransactionType;
import com.clevertec.cleverbank.repositories.AccountRepositoryImpl;
import com.clevertec.cleverbank.repositories.BankRepositoryImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckGenerator {
    private static final String CHECKS_FOLDER = "check/";
    private final AccountRepositoryImpl accountRepository;
    private final BankRepositoryImpl bankRepository;

    public CheckGenerator(AccountRepositoryImpl accountRepository, BankRepositoryImpl bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    public void generateCheck(Transaction transaction) {
        String checkFileName = CHECKS_FOLDER + "check_" + transaction.getId() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(checkFileName))) {
            writer.println("---------------------------------------");
            writer.println("|           Банковский чек            |");
            writer.printf("| Чек%32d |\n", transaction.getId());
            writer.printf("| %s-%s-%s                 %s:%s:%s |\n", transaction.getTime().getDayOfMonth(), transaction.getTime().getMonth().getValue(), transaction.getTime().getYear(), transaction.getTime().getHour(), transaction.getTime().getMinute(), transaction.getTime().getSecond());
            writer.printf("| Тип транзакции:%20s |\n", transaction.getType().getTranslate());
            if(transaction.getType() == TransactionType.DEPOSIT || transaction.getType() == TransactionType.WITHDRAW){
                writer.printf("| Банк получателя:%19s |\n", bankRepository.getBankById(accountRepository.getAccountById(transaction.getReceiverAccountId()).getBankId()).getName());
                writer.printf("| Счет получателя:%19s |\n", accountRepository.getAccountById(transaction.getReceiverAccountId()).getAccountNumber());
            } else {
                writer.printf("| Банк отправителя:%18s |\n", bankRepository.getBankById(accountRepository.getAccountById(transaction.getSenderAccountId()).getBankId()).getName());
                writer.printf("| Банк получателя:%19s |\n", bankRepository.getBankById(accountRepository.getAccountById(transaction.getReceiverAccountId()).getBankId()).getName());
                writer.printf("| Счет отправителя:%18s |\n", accountRepository.getAccountById(transaction.getSenderAccountId()).getAccountNumber());
                writer.printf("| Счет получателя:%19s |\n", accountRepository.getAccountById(transaction.getReceiverAccountId()).getAccountNumber());
            }
            writer.printf("| Сумма:%25s %3s |\n", transaction.getAmount().toString().replace('.', ','), accountRepository.getAccountById(transaction.getReceiverAccountId()).getCurrency());
            writer.println("---------------------------------------");

            System.out.println("Чек сформирован: " + checkFileName);
        } catch (IOException e) {
            System.err.println("Ошибка при формировании чека: " + e.getMessage());
        }
    }
}
