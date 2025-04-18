package com.canevi.fraud.detection.rules;

import com.canevi.fraud.detection.domain.FraudCheckResult;
import com.canevi.fraud.detection.domain.Transaction;

public interface FraudRule {
    String getRuleName();
    FraudCheckResult apply(Transaction transaction);
}
