package com.example.SmartShop.service;

import com.example.SmartShop.entity.BankAccount;
import com.example.SmartShop.repository.AccountRepository;
import com.example.SmartShop.repository.TransactionRepository;
import com.example.SmartShop.repository.UserRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }
    public BankAccount createAccount(Long userId) throws Throwable {
        User user = (User) userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setBalance(0.0);
        account.setUser(user);

        return accountRepository.save(account);
    }
    public BankAccount getAccount(Long accountId) throws Throwable {
        return (BankAccount) accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    @Transactional
    public String deposit(Long accountId, double amount) {
        if (amount <= 0) throw new RuntimeException("Invalid amount");

        BankAccount account = getAccount(accountId);
        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);

        saveTransaction(account, "DEPOSIT", amount);

        return "Amount deposited successfully. New Balance = " + account.getBalance();
    }

    // Withdraw Money
    @Transactional
    public String withdraw(Long accountId, double amount) {
        if (amount <= 0) throw new RuntimeException("Invalid amount");

        BankAccount account = getAccount(accountId);

        if (account.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        saveTransaction(account, "WITHDRAW", amount);

        return "Amount withdrawn successfully. New Balance = " + account.getBalance();
    }

    // Transfer Money
    @Transactional
    public String transfer(Long fromAccountId, Long toAccountId, double amount) {
        if (amount <= 0) throw new RuntimeException("Invalid amount");

        BankAccount fromAccount = getAccount(fromAccountId);
        BankAccount toAccount = getAccount(toAccountId);

        if (fromAccount.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        saveTransaction(fromAccount, "TRANSFER_OUT", amount);
        saveTransaction(toAccount, "TRANSFER_IN", amount);

        return "Transfer successful";
    }

    private void saveTransaction(BankAccount account, String type, double amount) {
        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setType(type);
        tx.setAmount(amount);
        tx.setDate(LocalDateTime.now());
        transactionRepository.save(tx);
    }

}
