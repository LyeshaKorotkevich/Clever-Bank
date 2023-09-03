package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.Bank;
import com.clevertec.cleverbank.repositories.BankRepositoryImpl;

/**
 * Меню для работы с банками.
 */
public class BankMenu extends Menu{
    private final BankRepositoryImpl bankRepository;
    private final static String BANK_MENU = """
            ---------------------------------------
            1: создать новый банк
            2: список всех банков
            3: информация о банке
            4: обновить информацию о банке
            5: удалить банк
            6: вернуться назад
            7: выйти
            ---------------------------------------""";

    public BankMenu(BankRepositoryImpl bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void start() {
        while (true) {
            printMenu(BANK_MENU);
            switch (scanner.nextInt()) {
                case 1 -> {
                    Bank bank = new Bank();
                    System.out.println("Введите название банка для создания:");
                    bank.setName(scanner.next());
                    bankRepository.createBank(bank);
                }
                case 2 -> bankRepository.getAllBanks().forEach(System.out::println);
                case 3 -> {
                    System.out.println("Введите название банка для поиска:");
                    System.out.println(bankRepository.getBankByName(scanner.next()));
                }
                case 4 -> {
                    System.out.println("Введите название банка для обновления:");
                    Bank bank = bankRepository.getBankByName(scanner.next());
                    System.out.println(bank);
                    System.out.println("Новое название банка:");
                    bank.setName(scanner.next());
                    bankRepository.updateBank(bank);
                }
                case 5 -> {
                    System.out.println("Введите название банка для удаления:");
                    Bank bank = bankRepository.getBankByName(scanner.next());
                    System.out.println(bank);
                    System.out.println("Удалять банк(да/нет)");
                    if(scanner.next().equals("да")) {
                        bankRepository.deleteBank(bank.getName());
                        System.out.println("Банк удален");
                    }
                }
                case 6 -> returnToPreviousMenu();
                case 7 -> System.exit(0);
            }
        }
    }
}
