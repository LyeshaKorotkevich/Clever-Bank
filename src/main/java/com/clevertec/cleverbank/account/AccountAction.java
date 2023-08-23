package com.clevertec.cleverbank.account;

public interface AccountAction{
    boolean openAccount();
    boolean closeAccount();
    double depositInCash(int accountId, int amount);
    boolean withdraw(int accountId, int amount);
    boolean convert(double amount);
    boolean transfer(int accountId, double amount);
}
