package com.chinmayshivratriwar.dev_playground.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions_CF")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relay_id", nullable = false, unique = true)
    private String relayId;

    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Column(name = "created_timestamp", nullable = false, updatable = false)
    private LocalDateTime createdTimestamp;

    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "crossborder_indicator")
    private Boolean crossborderIndicator;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "acquirer_number")
    private String acquirerNumber;

    @Column(name = "acquirer_country_code", length = 2)
    private String acquirerCountryCode;

    @Column(name = "issuer_number")
    private String issuerNumber;

    @Column(name = "issuer_country_code", length = 2)
    private String issuerCountryCode;

    @Column(name = "mode_of_transaction")
    private String modeOfTransaction;

    @Column(name = "country_of_transaction", length = 2)
    private String countryOfTransaction;

    @PrePersist
    protected void onCreate() {
        createdTimestamp = LocalDateTime.now();
        updatedTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTimestamp = LocalDateTime.now();
    }
}

