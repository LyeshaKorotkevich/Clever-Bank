package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Transaction;
import com.clevertec.cleverbank.repositories.TransactionRepositoryImpl;

public class TransactionMenu extends Menu{
    private final TransactionRepositoryImpl transactionRepository;
    private final static String TRANSACTION_MENU = """
            ---------------------------------------
            1: список всех транзакций
            2: определенная транзакция
            3: удалить транзакцию
            4: вернуться назад
            5: выйти
            ---------------------------------------""";

    public TransactionMenu(TransactionRepositoryImpl transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void start() {
        while (true) {
            printMenu(TRANSACTION_MENU);
            switch (scanner.nextInt()) {
                case 1 -> transactionRepository.getAllTransactions().forEach(System.out::println);
                case 2 -> {
                    System.out.println("Введите id транзакции для поиска:");
                    System.out.println(transactionRepository.getTransactionById(scanner.nextLong()));
                }
                case 3 -> {
                    System.out.println("Введите id транзакции для удаления:");
                    Transaction transaction = transactionRepository.getTransactionById(scanner.nextLong());
                    System.out.println(transaction);
                    System.out.println("Удалять транзакцию(да/нет)");
                    if(scanner.next().equals("да")) {
                        transactionRepository.deleteTransaction(transaction.getId());
                        System.out.println("Транзакция удалена");
                    }
                }
                case 4 -> returnToPreviousMenu();
                case 5 -> System.exit(0);
            }
        }
    }
}

