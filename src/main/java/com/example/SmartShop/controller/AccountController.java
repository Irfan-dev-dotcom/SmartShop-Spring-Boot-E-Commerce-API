package com.example.SmartShop.controller;

import com.example.SmartShop.entity.BankAccount;
import com.example.SmartShop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create/{userId}")
    public BankAccount createAccount(@PathVariable Long userId) throws Throwable {
        return accountService.createAccount(userId);
    }
    @GetMapping("/{accountId}")
    public BankAccount getAccount(PathVariable Long accountId){
        return accountService.getAccount(accountId);
    }
    @GetMapping("/deposit")
    public String deposit(@RequestParam Long accountId,
                          @RequestParam double amount){
        return accountService.deposit(accountId, amount);
    }
    @GetMapping("/withdeaw")
    public String withdraw(@RequestParam Long accountId,
                           @RequestParam double amount){
        return accountService.withdraw(accountId, amount);
    }
    @GetMapping("/transfer")
    public String transfer(@RequestParam Long fromAccountId,
                           @RequestParam Long toAccountId,
                           @RequestParam double amount){
        return accountService.transfer(fromAccountId, toAccountId, amount);
    }
}
