package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.model.Account;
import com.clevertec.cleverbank.repository.AccountRepository;
import com.clevertec.cleverbank.model.TransactionType;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionServiceImpl transactionService;
    private final Object lock = new Object();


    public AccountServiceImpl(AccountRepository accountRepository, TransactionServiceImpl transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.getAccountById(accountId);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountRepository.updateAccount(account);
            transactionService.createTransaction(TransactionType.DEPOSIT, accountId, accountId, amount);
        } else {
            //throw new AccountNotFoundException("Account not found");
        }
    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.getAccountById(accountId);
        if (account != null) {
            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
                accountRepository.updateAccount(account);
                transactionService.createTransaction(TransactionType.WITHDRAW, accountId, accountId, amount);
            } else {
                //throw new InsufficientBalanceException("Insufficient balance");
            }
        } else {
            //throw new AccountNotFoundException("Account not found");
        }
    }

    @Override
    public void transfer(long senderAccountId, long receiverAccountId, BigDecimal amount) {
        Account senderAccount = accountRepository.getAccountById(senderAccountId);
        Account receiverAccount = accountRepository.getAccountById(receiverAccountId);

        // Порядок блокировки по id аккаунта
        Object firstLock = senderAccountId < receiverAccountId ? senderAccount : receiverAccount;
        Object secondLock = senderAccountId < receiverAccountId ? receiverAccount : senderAccount;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (senderAccount.getBalance().compareTo(amount) >= 0) {
                    senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
                    receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
                    transactionService.createTransaction(TransactionType.TRANSFER, senderAccountId, receiverAccountId, amount);
                } else {
                    //throw new InsufficientBalanceException("Insufficient balance");
                }
            }
        }
    }
}
