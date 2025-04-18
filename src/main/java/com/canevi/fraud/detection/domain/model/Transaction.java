package com.canevi.fraud.detection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Transaction to be evaluated for fraud")
public class Transaction {

    @Schema(description = "Unique transaction ID", example = "txn-123456")
    private String transactionId;

    @Schema(description = "User ID associated with the transaction", example = "user-001")
    private String userId;

    @Schema(description = "Transaction amount", example = "15000.00")
    private BigDecimal amount;

    @Schema(description = "Currency code", example = "USD")
    private String currency;

    @Schema(description = "Country code (ISO 3166)", example = "RU")
    private String country;

    @Schema(description = "Transaction timestamp (ISO 8601)", example = "2024-04-18T10:15:30Z")
    private Date timestamp;
}