package com.canevi.fraud.detection.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FraudCheckResult {
    private String ruleName;
    private boolean isFraud;
    private String reason;
}
