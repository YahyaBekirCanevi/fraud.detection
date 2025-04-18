package com.canevi.fraud.detection.domain.rule.impl;

import com.canevi.fraud.detection.domain.model.FraudCheckResult;
import com.canevi.fraud.detection.domain.model.Transaction;
import com.canevi.fraud.detection.domain.rule.FraudRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HighAmountRule implements FraudRule {
    private static final BigDecimal THRESHOLD = new BigDecimal("10000");

    @Override
    public String getRuleName() {
        return "HighAmountRule";
    }

    @Override
    public FraudCheckResult apply(Transaction transaction) {
        if (transaction.getAmount().compareTo(THRESHOLD) > 0) {
            return new FraudCheckResult(getRuleName(), true, "Amount exceeds threshold");
        }
        return new FraudCheckResult(getRuleName(), false, "");
    }
}
