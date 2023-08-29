package com.clevertec.cleverbank.menu;

import java.util.Scanner;
import java.util.Stack;

public abstract class Menu {
    protected final Scanner scanner = new Scanner(System.in);
    protected static final Stack<Menu> menuStack = new Stack<>();

    public void pushMenu(Menu menu) {
        menuStack.push(menu);
    }

    protected void popMenu() {
        menuStack.pop();
    }

    protected void returnToPreviousMenu() {
        if (!menuStack.isEmpty()) {
            popMenu();
            menuStack.peek().start();
        }
    }

    protected void printMenu(String menu) {
        System.out.println(menu);
    }

    public abstract void start();
}
