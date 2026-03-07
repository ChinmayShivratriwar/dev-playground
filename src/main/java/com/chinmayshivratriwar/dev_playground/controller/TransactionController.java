package com.chinmayshivratriwar.dev_playground.controller;

import com.chinmayshivratriwar.dev_playground.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/save-transaction")
    public ResponseEntity<String> saveInBulk() {
        try {
            long start = System.currentTimeMillis();
            transactionService.saveRandomTransactionInBulk();
            long end = System.currentTimeMillis();
            return ResponseEntity.ok("Time taken to save transactions: " + (end - start) + " ms");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing transaction: " + e.getMessage());
        }
    }

    @GetMapping("/save-transaction/CF")
    public ResponseEntity<String> saveInBulkCF() {
        try {
            long start = System.currentTimeMillis();
            transactionService.saveRandomTransactionInBulkWithCompletableFuture();
            long end = System.currentTimeMillis();
            return ResponseEntity.ok("Time taken to save transactions: " + (end - start) + " ms");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing transaction: " + e.getMessage());
        }
    }

    @GetMapping("/save-transaction/ES")
    public ResponseEntity<String> saveInBulkES() {
        try {
            long start = System.currentTimeMillis();
            transactionService.saveRandomTransactionInBulkWithExecutorService();
            long end = System.currentTimeMillis();
            return ResponseEntity.ok("Time taken to save transactions: " + (end - start) + " ms");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing transaction: " + e.getMessage());
        }
    }
}
