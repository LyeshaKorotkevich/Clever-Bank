package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.models.Bank;
import com.clevertec.cleverbank.repositories.AccountRepositoryImpl;

public class AccountMenu extends Menu{
    private final AccountRepositoryImpl accountRepository;
    private final static String ACCOUNT_MENU =  "---------------------------------------\n" +
                                                "1: создать новый счет\n" +
                                                "2: список всех счетов\n" +
                                                "3: определенный счет\n" +
                                                "4: обновить информацию о счете\n" +
                                                "5: удалить счет\n" +
                                                "6: вернуться назад\n" +
                                                "7: выйти\n" +
                                                "---------------------------------------";

    public AccountMenu(AccountRepositoryImpl accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void start() {
        while (true) {
            printMenu(ACCOUNT_MENU);
            switch (scanner.nextInt()) {
//                case 1 -> {
//                    Account account = new Account();
//                    System.out.println("Введите название счета для создания:");
//                    account.setName(scanner.next());
//                    bankRepository.createBank(bank);
//                }
//                case 2 -> accountRepository.getAllAccounts().forEach(System.out::println);
//                case 3 -> {
//                    System.out.println("Введите название банка для поиска:");
//                    System.out.println(bankRepository.getBankByName(scanner.next()));
//                }
//                case 4 -> {
//                    System.out.println("Введите название банка для обновления:");
//                    Bank bank = bankRepository.getBankByName(scanner.next());
//                    System.out.println(bank);
//                    System.out.println("Новое название банка:");
//                    bank.setName(scanner.next());
//                    bankRepository.updateBank(bank);
//                }
//                case 5 -> {
//                    System.out.println("Введите название банка для удаления:");
//                    Bank bank = bankRepository.getBankByName(scanner.next());
//                    System.out.println(bank);
//                    System.out.println("Удалять банк(да/нет)");
//                    if (scanner.next().equals("да")) {
//                        bankRepository.deleteBank(bank.getName());
//                        System.out.println("Банк удален");
//                    }
//                }
                case 6:
                    returnToPreviousMenu();
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }
}
