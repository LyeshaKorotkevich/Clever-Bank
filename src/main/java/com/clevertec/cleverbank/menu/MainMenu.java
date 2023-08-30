package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.repositories.*;
import com.clevertec.cleverbank.services.AccountServiceImpl;
import com.clevertec.cleverbank.services.TransactionServiceImpl;

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
            4: операции с пользователями
            5: операции с банками
            6: операции со счетами
            7: операции с транзакциями
            8: выход
            ---------------------------------------""";

    public MainMenu(Connection connection) {
        this.userRepository = new UserRepositoryImpl(connection);
        this.bankRepository = new BankRepositoryImpl(connection);
        this.accountRepository = new AccountRepositoryImpl(connection);
        this.transactionRepository = new TransactionRepositoryImpl(connection);

        TransactionServiceImpl transactionService = new TransactionServiceImpl(transactionRepository);
        this.accountService = new AccountServiceImpl(accountRepository, transactionService);
    }

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
                    UserMenu userMenu = new UserMenu(userRepository);
                    pushMenu(userMenu);
                    userMenu.start();
                }
                case 5 -> {
                    BankMenu bankMenu = new BankMenu(bankRepository);
                    pushMenu(bankMenu);
                    bankMenu.start();
                }
                case 6 -> {
                    AccountMenu accountMenu = new AccountMenu(accountRepository, bankRepository, userRepository);
                    pushMenu(accountMenu);
                    accountMenu.start();
                }
                case 7 -> {
                    TransactionMenu transactionMenu = new TransactionMenu(transactionRepository);
                    pushMenu(transactionMenu);
                    transactionMenu.start();
                }
                case 8 -> System.exit(0);
            }
        }
    }
}
