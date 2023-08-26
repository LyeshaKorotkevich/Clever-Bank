package com.clevertec.cleverbank.bank;

import java.util.List;

public interface BankRepository {
    void createBank(Bank bank);
    Bank getBankById(long bankId);
    List<Bank> getAllBanks();
    void updateBank(Bank bank);
    void deleteBank(long bankId);
}
