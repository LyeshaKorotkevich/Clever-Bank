package com.clevertec.cleverbank.menu;

import java.util.Scanner;
import java.util.Stack;

/**
 * Абстрактное меню.
 */
public abstract class Menu {
    protected final Scanner scanner = new Scanner(System.in);
    protected static final Stack<Menu> menuStack = new Stack<>();

    /**
     * Добавить меню в стэк.
     * @param menu
     */
    public void pushMenu(Menu menu) {
        menuStack.push(menu);
    }

    /**
     * Извлечь меню из стэка.
     */
    protected void popMenu() {
        menuStack.pop();
    }

    /**
     * Вернуться в предыдущее меню.
     */
    protected void returnToPreviousMenu() {
        if (!menuStack.isEmpty()) {
            popMenu();
            menuStack.peek().start();
        }
    }

    /**
     * Вывести меню на экран.
     * @param menu
     */
    protected void printMenu(String menu) {
        System.out.println(menu);
    }

    /**
     * Запуск меню.
     */
    public abstract void start();
}
