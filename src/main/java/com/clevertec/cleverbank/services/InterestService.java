package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.repositories.AccountRepositoryImpl;
import com.clevertec.cleverbank.util.ConfigReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Серви для расчета и начисления процентов на балансы аккаунтов.
 */
public class InterestService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AccountRepositoryImpl accountRepository;
    private final AccountServiceImpl accountService;

    public InterestService(AccountRepositoryImpl accountRepository, AccountServiceImpl accountService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    /**
     * Запускает расчет и начисление процентов на балансы аккаунтов каждые 30 секунд.
     */
    public void startInterestCalculation() {
        scheduler.scheduleAtFixedRate(this::calculateInterest, 0, 30, TimeUnit.SECONDS);
    }

    /**
     * Выполняет расчет и начисление процентов на балансы аккаунтов.
     */
    private void calculateInterest() {

        List<Account> accounts = accountRepository.getAllAccounts();
        for (Account account : accounts) {
            BigDecimal balance = account.getBalance();
            BigDecimal interestRate = ConfigReader.getInterestRate();
            BigDecimal interestAmount = balance.multiply(interestRate);

            accountService.deposit(account.getId(), interestAmount);
        }
    }
}

