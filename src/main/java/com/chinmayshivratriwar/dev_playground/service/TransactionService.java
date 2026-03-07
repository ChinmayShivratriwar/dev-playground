package com.chinmayshivratriwar.dev_playground.service;

import com.chinmayshivratriwar.dev_playground.entity.TransactionTable;
import com.chinmayshivratriwar.dev_playground.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public String saveRandomTransactionInBulk() {
        Random random = new Random();
        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY"};
        String[] countries = {"US", "IN", "GB", "DE", "JP"};

        for (int i = 0; i < 1000; i++) {
            TransactionTable txn = TransactionTable.builder()
                    .relayId(UUID.randomUUID().toString())
                    .correlationId(UUID.randomUUID().toString())
                    .createdTimestamp(LocalDateTime.now())
                    .updatedTimestamp(LocalDateTime.now())
                    .amount(BigDecimal.valueOf(random.nextInt(10000) + random.nextDouble()))
                    .currencyCode(currencies[random.nextInt(currencies.length)])
                    .crossborderIndicator(random.nextBoolean())
                    .customerNumber("CUST" + random.nextInt(10000))
                    .acquirerNumber("ACQ" + random.nextInt(10000))
                    .acquirerCountryCode(countries[random.nextInt(countries.length)])
                    .issuerNumber("ISS" + random.nextInt(10000))
                    .issuerCountryCode(countries[random.nextInt(countries.length)])
                    .modeOfTransaction(random.nextBoolean() ? "CARD" : "ONLINE")
                    .countryOfTransaction(countries[random.nextInt(countries.length)])
                    .build();

            transactionRepository.save(txn);
        }


        System.out.println("Seeded 1000 random transactions successfully!");
        return "Seeded 1000 random transactions successfully!";
    }

    public String saveRandomTransactionInBulkWithCompletableFuture() {
        Random random = new Random();
        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY"};
        String[] countries = {"US", "IN", "GB", "DE", "JP"};

        for (int i = 0; i < 1000; i++) {
            CompletableFuture.runAsync(() -> {
                TransactionTable txn = TransactionTable.builder()
                        .relayId(UUID.randomUUID().toString())
                        .correlationId(UUID.randomUUID().toString())
                        .createdTimestamp(LocalDateTime.now())
                        .updatedTimestamp(LocalDateTime.now())
                        .amount(BigDecimal.valueOf(random.nextInt(10000) + random.nextDouble()))
                        .currencyCode(currencies[random.nextInt(currencies.length)])
                        .crossborderIndicator(random.nextBoolean())
                        .customerNumber("CUST" + random.nextInt(10000))
                        .acquirerNumber("ACQ" + random.nextInt(10000))
                        .acquirerCountryCode(countries[random.nextInt(countries.length)])
                        .issuerNumber("ISS" + random.nextInt(10000))
                        .issuerCountryCode(countries[random.nextInt(countries.length)])
                        .modeOfTransaction(random.nextBoolean() ? "CARD" : "ONLINE")
                        .countryOfTransaction(countries[random.nextInt(countries.length)])
                        .build();

                transactionRepository.save(txn);
            }).handle((result, ex) -> {;
                if (ex != null) {
                    System.out.println("Error saving transaction: " + ex.getMessage());
                }else {
                    System.out.println("Task finished cleanly");
                }
                return null;
            });
        }

        System.out.println("Seeded 1000 random transactions successfully!");
        return "Seeded 1000 random transactions successfully!";
    }

    public String saveRandomTransactionInBulkWithExecutorService() {
        Random random = new Random();
        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY"};
        String[] countries = {"US", "IN", "GB", "DE", "JP"};

        // Create a fixed thread pool with 10 threads
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                TransactionTable txn = TransactionTable.builder()
                        .relayId(UUID.randomUUID().toString())
                        .correlationId(UUID.randomUUID().toString())
                        .createdTimestamp(LocalDateTime.now())
                        .updatedTimestamp(LocalDateTime.now())
                        .amount(BigDecimal.valueOf(random.nextInt(10000) + random.nextDouble()))
                        .currencyCode(currencies[random.nextInt(currencies.length)])
                        .crossborderIndicator(random.nextBoolean())
                        .customerNumber("CUST" + random.nextInt(10000))
                        .acquirerNumber("ACQ" + random.nextInt(10000))
                        .acquirerCountryCode(countries[random.nextInt(countries.length)])
                        .issuerNumber("ISS" + random.nextInt(10000))
                        .issuerCountryCode(countries[random.nextInt(countries.length)])
                        .modeOfTransaction(random.nextBoolean() ? "CARD" : "ONLINE")
                        .countryOfTransaction(countries[random.nextInt(countries.length)])
                        .build();

                transactionRepository.save(txn);
            });
        }

        // Shutdown executor and wait for all tasks to complete
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
                System.out.println("ExecutorService did not terminate in time, forcing shutdown!");
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("ExecutorService interrupted: " + e.getMessage());
        }

        System.out.println("Seeded 1000 random transactions successfully with ExecutorService!");
        return "Seeded 1000 random transactions successfully with ExecutorService!";
    }
}


