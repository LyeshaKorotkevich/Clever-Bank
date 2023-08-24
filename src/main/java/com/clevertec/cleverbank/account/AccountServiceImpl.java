package com.clevertec.cleverbank.account;

import com.clevertec.cleverbank.transaction.TransactionServiceImpl;
import com.clevertec.cleverbank.transaction.TransactionType;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final TransactionServiceImpl transactionService;


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
    public void transfer(long sourceAccountId, long targetAccountId, double amount) {

    }
}
