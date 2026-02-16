package com.example.SmartShop.controller;

import com.example.SmartShop.service.TransactionService;
import jakarta.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Get transaction history with pagination
    @GetMapping("/{accountId}")
    public Page<Transaction> getTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return transactionService.getTransactions(accountId, page, size);
    }
}
