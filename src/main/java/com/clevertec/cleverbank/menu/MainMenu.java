package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.models.Bank;
import com.clevertec.cleverbank.repositories.*;
import com.clevertec.cleverbank.services.AccountServiceImpl;
import com.clevertec.cleverbank.services.TransactionServiceImpl;
import com.clevertec.cleverbank.util.CheckGenerator;
import com.clevertec.cleverbank.util.StatementGenerator;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

public class MainMenu extends Menu{
    private final UserRepositoryImpl userRepository;
    private final BankRepositoryImpl bankRepository;
    private final AccountRepositoryImpl accountRepository;
    private final TransactionRepositoryImpl transactionRepository;
    private final AccountServiceImpl accountService;
    private final static String MENU = """
            ---------------------------------------
            1: пополнить баланс на счету
            2: снять деньги со счета
            3: перевести деньги на другой счет
            4: запросить выписку у банка
            5: операции с пользователями
            6: операции с банками
            7: операции со счетами
            8: операции с транзакциями
            9: выход
            ---------------------------------------""";

    public MainMenu(Connection connection) {
        this.userRepository = new UserRepositoryImpl(connection);
        this.bankRepository = new BankRepositoryImpl(connection);
        this.accountRepository = new AccountRepositoryImpl(connection);
        this.transactionRepository = new TransactionRepositoryImpl(connection);

        CheckGenerator checkGenerator = new CheckGenerator(accountRepository, bankRepository);

        TransactionServiceImpl transactionService = new TransactionServiceImpl(transactionRepository);
        this.accountService = new AccountServiceImpl(accountRepository, transactionService, checkGenerator);
    }

    //добавить проверки
    @Override
    public void start() {
        while (true) {
            printMenu(MENU);
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("Введите id счета для пополнения:");
                    Account account = accountRepository.getAccountById(scanner.nextLong());
                    System.out.println("Введите сумму для пополнения:");
                    accountService.deposit(account.getId(), scanner.nextBigDecimal());
                }
                case 2 -> {
                    System.out.println("Введите id счета для снятия:");
                    Account account = accountRepository.getAccountById(scanner.nextLong());
                    System.out.println("Введите сумму для снятия:");
                    accountService.withdraw(account.getId(), scanner.nextBigDecimal());
                }
                case 3 -> {
                    System.out.println("Введите id счета с какого будем отправлять:");
                    Account senderAccount = accountRepository.getAccountById(scanner.nextLong());
                    System.out.println("Введите id счета на который будем отправлять:");
                    Account recieverAccount = accountRepository.getAccountById(scanner.nextLong());
                    System.out.println("Введите сумму для перевода:");
                    accountService.transfer(senderAccount.getId(),recieverAccount.getId(), scanner.nextBigDecimal());
                }
                case 4 -> {
                    System.out.println("Введите название банка у которого хотите запросить выписку:");
                    Bank bank = bankRepository.getBankByName(scanner.next());
                    System.out.println("Введите id счета по которому будет сформирована выписка:");
                    Account account = accountRepository.getAccountById(scanner.nextLong());
                    StatementGenerator statementGenerator = new StatementGenerator(accountRepository, bankRepository, userRepository);
                    try {
                        statementGenerator.generate(bank, account);
                    } catch (DocumentException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 5 -> {
                    UserMenu userMenu = new UserMenu(userRepository);
                    pushMenu(userMenu);
                    userMenu.start();
                }
                case 6 -> {
                    BankMenu bankMenu = new BankMenu(bankRepository);
                    pushMenu(bankMenu);
                    bankMenu.start();
                }
                case 7 -> {
                    AccountMenu accountMenu = new AccountMenu(accountRepository, bankRepository, userRepository);
                    pushMenu(accountMenu);
                    accountMenu.start();
                }
                case 8 -> {
                    TransactionMenu transactionMenu = new TransactionMenu(transactionRepository);
                    pushMenu(transactionMenu);
                    transactionMenu.start();
                }
                case 9 -> System.exit(0);
            }
        }
    }
}
