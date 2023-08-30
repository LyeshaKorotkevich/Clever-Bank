package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.models.Currency;
import com.clevertec.cleverbank.repositories.AccountRepositoryImpl;
import com.clevertec.cleverbank.repositories.BankRepositoryImpl;
import com.clevertec.cleverbank.repositories.UserRepositoryImpl;

public class AccountMenu extends Menu{
    private final AccountRepositoryImpl accountRepository;
    private final BankRepositoryImpl bankRepository;
    private final UserRepositoryImpl userRepository;
    private final static String ACCOUNT_MENU =  "---------------------------------------\n" +
                                                "1: создать новый счет\n" +
                                                "2: список всех счетов\n" +
                                                "3: определенный счет\n" +
                                                "4: обновить информацию о счете\n" +
                                                "5: удалить счет\n" +
                                                "6: вернуться назад\n" +
                                                "7: выйти\n" +
                                                "---------------------------------------";

    public AccountMenu(AccountRepositoryImpl accountRepository, BankRepositoryImpl bankRepository, UserRepositoryImpl userRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void start() {
        while (true) {
            printMenu(ACCOUNT_MENU);
            switch (scanner.nextInt()) {
                case 1 -> {
                    Account account = new Account();
                    System.out.println("Введите id пользователя, желающего открыть счет:");
                    account.setUserId(userRepository.getUserById(scanner.nextLong()).getId());
                    System.out.println("Введите название банка, где хотите открыть счет:");
                    account.setBankId(bankRepository.getBankByName(scanner.next()).getId());
                    while (true) {
                        System.out.println("В какой валюте хотите открыть счет (BYN, USD, EUR, RUB):");
                        String currencyInput = scanner.next().toUpperCase();
                        switch (currencyInput) {
                            case "BYN" -> account.setCurrency(Currency.BYN);
                            case "USD" -> account.setCurrency(Currency.USD);
                            case "EUR" -> account.setCurrency(Currency.EUR);
                            case "RUB" -> account.setCurrency(Currency.RUB);
                            default -> {
                                System.out.println("Неверный ввод. Пожалуйста, выберите одну из указанных валют.");
                                continue;
                            }
                        }
                        break;
                    }
                    accountRepository.createAccount(account);
                }
                case 2 -> accountRepository.getAllAccounts().forEach(System.out::println);
                case 3 -> {
                    System.out.println("Введите id счета для поиска:");
                    System.out.println(accountRepository.getAccountById(scanner.nextLong()));
                }
                case 4 -> {
                    System.out.println("Введите id счета для обновления:");
                    Account account = accountRepository.getAccountById(scanner.nextLong());
                    System.out.println(account);
                    System.out.println("Введите id нового владельца счета:");
                    account.setUserId(scanner.nextLong());
                    accountRepository.updateAccount(account);
                }
                case 5 -> {
                    System.out.println("Введите id счета для удаления:");
                    Account account = accountRepository.getAccountById(scanner.nextLong());
                    System.out.println(account);
                    System.out.println("Удалять счет(да/нет)");
                    if (scanner.next().equals("да")) {
                        accountRepository.deleteAccount(account.getId());
                        System.out.println("Счет удален");
                    }
                }
                case 6 -> returnToPreviousMenu();
                case 7 -> System.exit(0);
            }
        }
    }
}
