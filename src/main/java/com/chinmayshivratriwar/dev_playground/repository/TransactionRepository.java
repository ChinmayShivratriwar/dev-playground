package com.chinmayshivratriwar.dev_playground.repository;

import com.chinmayshivratriwar.dev_playground.entity.TransactionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionTable, Long> {

    Optional<TransactionTable> findByRelayId(String relayId);

    List<TransactionTable> findByCorrelationId(String correlationId);

    List<TransactionTable> findByCustomerNumber(String customerNumber);

    List<TransactionTable> findByAcquirerNumber(String acquirerNumber);

    List<TransactionTable> findByIssuerNumber(String issuerNumber);

    List<TransactionTable> findByCurrencyCode(String currencyCode);

    List<TransactionTable> findByModeOfTransaction(String modeOfTransaction);

    List<TransactionTable> findByCountryOfTransaction(String countryCode);

    List<TransactionTable> findByAcquirerCountryCode(String countryCode);

    List<TransactionTable> findByIssuerCountryCode(String countryCode);

    @Query("SELECT t FROM TransactionTable t WHERE t.amount BETWEEN :minAmount AND :maxAmount")
    List<TransactionTable> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount);

    @Query("SELECT t FROM TransactionTable t WHERE t.createdTimestamp BETWEEN :startDate AND :endDate")
    List<TransactionTable> findByCreatedDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM TransactionTable t WHERE t.crossborderIndicator = true")
    List<TransactionTable> findCrossborderTransactions();

    @Query("SELECT t FROM TransactionTable t WHERE t.createdTimestamp >= :thresholdTime")
    List<TransactionTable> findTransactionsInLastMinutes(@Param("thresholdTime") LocalDateTime thresholdTime);

    @Query("SELECT t FROM TransactionTable t WHERE t.acquirerCountryCode = :countryCode OR t.issuerCountryCode = :countryCode")
    List<TransactionTable> findTransactionsByCountry(@Param("countryCode") String countryCode);
}


