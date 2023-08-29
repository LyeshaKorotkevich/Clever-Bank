package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.repositories.*;

import java.sql.Connection;

public class MainMenu extends Menu{
    private final UserRepositoryImpl userRepository;
    private final BankRepositoryImpl bankRepository;
    private final AccountRepositoryImpl accountRepository;
    private final TransactionRepositoryImpl transactionRepository;
    private final static String MENU =  "---------------------------------------\n" +
                                        "1: операции с пользователями\n" +
                                        "2: операции с банками\n" +
                                        "3: операции со счетами\n" +
                                        "4: операции с транзакциями\n" +
                                        "5: выход\n" +
                                        "---------------------------------------";

    public MainMenu(Connection connection) {
        this.userRepository = new UserRepositoryImpl(connection);
        this.bankRepository = new BankRepositoryImpl(connection);
        this.accountRepository = new AccountRepositoryImpl(connection);
        this.transactionRepository = new TransactionRepositoryImpl(connection);
    }

    @Override
    public void start() {
        while (true) {
            printMenu(MENU);
            switch (scanner.nextInt()) {
                case 1 -> {
                    UserMenu userMenu = new UserMenu(userRepository);
                    pushMenu(userMenu);
                    userMenu.start();
                }
                case 2 -> {
                    BankMenu bankMenu = new BankMenu(bankRepository);
                    pushMenu(bankMenu);
                    bankMenu.start();
                }
                case 3 -> {
                    AccountMenu accountMenu = new AccountMenu(accountRepository);
                    pushMenu(accountMenu);
                    accountMenu.start();
                }
                case 4 -> {
                    TransactionMenu transactionMenu = new TransactionMenu(transactionRepository);
                    pushMenu(transactionMenu);
                    transactionMenu.start();
                }
                case 5 -> System.exit(0);
            }
        }
    }
}
