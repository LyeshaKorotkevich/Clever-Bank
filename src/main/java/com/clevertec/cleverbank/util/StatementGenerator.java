package com.clevertec.cleverbank.util;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.models.Bank;
import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.repositories.TransactionRepositoryImpl;
import com.clevertec.cleverbank.repositories.UserRepositoryImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class StatementGenerator {

    private static final String STATEMENTS_FOLDER = "statements/";
    private final UserRepositoryImpl userRepository;
    private final TransactionRepositoryImpl transactionRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public StatementGenerator(UserRepositoryImpl userRepository, TransactionRepositoryImpl transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public void generate(Bank bank, Account account, TimePeriod period) throws DocumentException, IOException {
        String statement = generateStatement(bank, account, period);
        generatePdfStatement(statement, account);
        generateTxtStatement(statement, account);
    }

    private String generateStatement(Bank bank, Account account, TimePeriod period) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = switch (period){
            case MONTH -> endDate.minusMonths(1);
            case YEAR -> endDate.minusYears(1);
            case ALL_TIME -> account.getOpeningDate();
        };


        String statement = "                       Выписка\n" +
                "                       " + bank.getName() + "\n" +
                "Клиент                    | " + userRepository.getUserById(account.getUserId()).getFullName() + "\n" +
                "Счет                      | " + account.getAccountNumber() + "\n" +
                "Валюта                    | " + account.getCurrency().getCode() + "\n" +
                "Дата открытия             | " + dateFormat.format(Date.from(account.getOpeningDate().atStartOfDay(ZoneId.systemDefault()).toInstant())) + "\n" +
                "Период                    | \n" + dateFormat.format(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant())) + " - " + dateFormat.format(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())) + "\n" +
                "Дата и время формирования | " + dateFormat.format(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())) + "\n" +
                "Остаток                   | " + account.getBalance() + " " + account.getCurrency().getCode() + "\n" +
                "  Дата     |     Примечание                     | Сумма\n" +
                "--------------------------------------------------------------\n" +
                generateTransactions(account, period);

        return statement;
    }

    private String generateTransactions(Account account, TimePeriod period) {
        StringBuilder builder = new StringBuilder();
        List<Transaction> transactions;
        if(period == TimePeriod.ALL_TIME)
            transactions = transactionRepository.getTransactionsByUser(account.getUserId());
        else transactions = transactionRepository.getTransactionsByPeriod(account.getUserId(), period);

        for(Transaction transaction : transactions){
            Date date = Date.from(transaction.getTime().atZone(ZoneId.systemDefault()).toInstant());
            builder.append(dateFormat.format(date)).append(" | ");
            switch (transaction.getType()){
                case DEPOSIT ->
                    builder.append("Пополнение баланса                 | ")
                            .append(transaction.getAmount()).append(" ")
                            .append(account.getCurrency().getCode());
                case WITHDRAW ->
                    builder.append("Снятие средств                     | ")
                            .append("-").append(transaction.getAmount()).append(" ")
                            .append(account.getCurrency().getCode());
                case TRANSFER -> {
                    if(transaction.getReceiverAccountId().equals(account.getUserId())){
                        builder.append("Пополнение от ").append(String.format("%-21s", userRepository.getUserById(transaction.getSenderAccountId()).getSurname())).append("|")
                                .append(transaction.getAmount()).append(" ")
                                .append(account.getCurrency().getCode());
                    } else {
                        builder.append("Перевод ").append(String.format("%-27s", userRepository.getUserById(transaction.getReceiverAccountId()).getSurname())).append("|")
                                .append("-").append(transaction.getAmount()).append(" ")
                                .append(account.getCurrency().getCode());
                    }
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private void generatePdfStatement(String statement, Account account) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(STATEMENTS_FOLDER + "statement_" + account.getAccountNumber() + ".pdf"));

        document.open();
        try {
            PdfPTable table = new PdfPTable(1);

            Font font = FontFactory.getFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);
            String[] lines = statement.split("\n");

            for (String line : lines) {
                PdfPCell cell = new PdfPCell(new Phrase(line, font));
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
            }

            document.add(table);
        } finally {
            document.close();
            writer.close();
        }
    }

    private void generateTxtStatement(String statement, Account account) throws IOException {
        String txtFileName = STATEMENTS_FOLDER + "statement_" + account.getAccountNumber() + ".txt";

        try (FileWriter writer = new FileWriter(txtFileName)) {
            writer.write(statement);
        }
    }
}
