package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Bank;
import com.clevertec.cleverbank.repositories.TransactionRepositoryImpl;

public class TransactionMenu extends Menu{
    private final TransactionRepositoryImpl transactionRepository;
    private final static String TRANSACTION_MENU =  "---------------------------------------\n" +
                                                    "1: создать новую транзакцию\n" +
                                                    "2: список всех транзакций\n" +
                                                    "3: определенная транзакция\n" +
                                                    "4: обновить информацию о транзакции\n" +
                                                    "5: удалить транзакцию\n" +
                                                    "6: вернуться назад\n" +
                                                    "7: выйти\n" +
                                                    "---------------------------------------";

    public TransactionMenu(TransactionRepositoryImpl transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void start() {
        while (true) {
            printMenu(TRANSACTION_MENU);
            switch (scanner.nextInt()) {
                case 1:
                    Bank bank = new Bank();
                    //bankRepository.createBank();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    returnToPreviousMenu();
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }
}

