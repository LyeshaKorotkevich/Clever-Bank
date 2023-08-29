package com.clevertec.cleverbank.menu;

import com.clevertec.cleverbank.models.User;
import com.clevertec.cleverbank.repositories.UserRepositoryImpl;

public class UserMenu extends Menu{
    private final UserRepositoryImpl userRepository;
    private final static String USER_MENU = "---------------------------------------\n" +
                                            "1: создать нового пользователя\n" +
                                            "2: список всех пользователей\n" +
                                            "3: определенный пользователь\n" +
                                            "4: обновить информацию о пользователе\n" +
                                            "5: удалить пользователя\n" +
                                            "6: вернуться назад\n" +
                                            "7: выйти\n" +
                                            "---------------------------------------";

    public UserMenu(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void start() {
        while (true) {
            printMenu(USER_MENU);
            switch (scanner.nextInt()) {
                case 1 -> {
                    User user = new User();
                    System.out.println("Введите фамилию нового пользователя:");
                    user.setSurname(scanner.next());
                    System.out.println("Введите имя нового пользователя:");
                    user.setName(scanner.next());
                    System.out.println("Введите отчество нового пользователя:");
                    user.setMiddleName(scanner.next());
                    userRepository.createUser(user);
                }
                case 2 -> userRepository.getAllUsers().forEach(System.out::println);
                case 3 -> {
                    System.out.println("Введите id пользователя для поиска:");
                    System.out.println(userRepository.getUserById(scanner.nextInt()));
                }
                case 4 -> {
                    System.out.println("Введите id пользователя для обновления:");
                    User user = userRepository.getUserById(scanner.nextInt());
                    System.out.println(user);
                    System.out.println("Новая фамилия пользователя:");
                    user.setSurname(scanner.next());
                    System.out.println("Новое имя пользователя:");
                    user.setName(scanner.next());
                    System.out.println("Новое отчество пользователя:");
                    user.setMiddleName(scanner.next());
                    userRepository.updateUser(user);
                }
                case 5 -> {
                    System.out.println("Введите id пользователя для удаления:");
                    User user = userRepository.getUserById(scanner.nextInt());
                    System.out.println(user);
                    System.out.println("Удалять пользователя(да/нет)");
                    if(scanner.next().equals("да")) {
                        userRepository.deleteUser(user.getId());
                        System.out.println("Пользователь удален");
                    }
                }
                case 6 -> returnToPreviousMenu();
                case 7 -> System.exit(0);
            }
        }
    }
}
