package com.clevertec.cleverbank.services;

import com.clevertec.cleverbank.models.Account;
import com.clevertec.cleverbank.models.TransactionType;
import com.clevertec.cleverbank.repositories.AccountRepositoryImpl;
import com.clevertec.cleverbank.util.CheckGenerator;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * Реализация интерфейса AccountService для выполнения операций с аккаунтами.
 */
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepositoryImpl accountRepository;
    private final TransactionServiceImpl transactionService;
    private final CheckGenerator checkGenerator;


    public AccountServiceImpl(AccountRepositoryImpl accountRepository, TransactionServiceImpl transactionService, CheckGenerator checkGenerator) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.checkGenerator = checkGenerator;
    }

    @Override
    public void deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.getAccountById(accountId);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountRepository.updateAccount(account);
            checkGenerator.generateCheck(transactionService.createTransaction(TransactionType.DEPOSIT, accountId, accountId, amount));
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
                checkGenerator.generateCheck(transactionService.createTransaction(TransactionType.WITHDRAW, accountId, accountId, amount));
            } else {
                //throw new InsufficientBalanceException("Insufficient balance");
            }
        } else {
            //throw new AccountNotFoundException("Account not found");
        }
    }

    @Override
    public void transfer(Long senderAccountId, Long receiverAccountId, BigDecimal amount) {
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
                    accountRepository.updateAccount(senderAccount);
                    accountRepository.updateAccount(receiverAccount);
                    checkGenerator.generateCheck(transactionService.createTransaction(TransactionType.TRANSFER, senderAccountId, receiverAccountId, amount));
                } else {
                    //throw new InsufficientBalanceException("Insufficient balance");
                }
            }
        }
    }
}
