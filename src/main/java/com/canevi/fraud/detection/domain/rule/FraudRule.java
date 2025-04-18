package com.canevi.fraud.detection.domain.rule;

import com.canevi.fraud.detection.domain.model.FraudCheckResult;
import com.canevi.fraud.detection.domain.model.Transaction;

public interface FraudRule {
    String getRuleName();
    FraudCheckResult apply(Transaction transaction);
}
