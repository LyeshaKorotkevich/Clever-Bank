package com.clevertec.cleverbank.util;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.models.Bank;
import com.clevertec.cleverbank.repositories.AccountRepositoryImpl;
import com.clevertec.cleverbank.repositories.BankRepositoryImpl;
import com.clevertec.cleverbank.repositories.UserRepositoryImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class StatementGenerator {

    private static final String STATEMENTS_FOLDER = "statements/";
    private final AccountRepositoryImpl accountRepository;
    private final BankRepositoryImpl bankRepository;
    private final UserRepositoryImpl userRepository;

    public StatementGenerator(AccountRepositoryImpl accountRepository, BankRepositoryImpl bankRepository, UserRepositoryImpl userRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
    }

    public void generate(Bank bank, Account account) throws DocumentException, IOException {
        String statement = generateStatement(bank, account);
        generatePdfStatement(statement, account);
        generateTxtStatement(statement, account);
    }

    private String generateStatement(Bank bank, Account account) {
        StringBuilder builder = new StringBuilder();

        builder.append("                        Выписка\n");
        builder.append("                       ").append(bank.getName()).append("\n");
        builder.append("Клиент                    | ").append(userRepository.getUserById(account.getUserId()).getFullName()).append("\n");
        builder.append("Счет                      | ").append(account.getAccountNumber()).append("\n");
        builder.append("Валюта                    | ").append(account.getCurrency().getCode()).append("\n");
        builder.append("Дата открытия             | ").append(account.getOpeningDate()).append("\n");
        builder.append("Период                    | \n");
        builder.append("Дата и время формирования | ").append(LocalDate.now()).append("\n");
        builder.append("Остаток                   | ").append(account.getBalance()).append(" ").append(account.getCurrency().getCode()).append("\n");
        builder.append("  Дата     |     Примечание                     | Сумма\n");
        builder.append("---------------------------------------------------------\n");


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
