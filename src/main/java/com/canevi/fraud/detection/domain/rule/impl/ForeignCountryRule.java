package com.canevi.fraud.detection.domain.rule.impl;

import com.canevi.fraud.detection.domain.model.FraudCheckResult;
import com.canevi.fraud.detection.domain.model.Transaction;
import com.canevi.fraud.detection.domain.rule.FraudRule;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ForeignCountryRule implements FraudRule {
    private static final Set<String> TRUSTED_COUNTRIES = Set.of("US", "UK", "DE");

    @Override
    public String getRuleName() {
        return "ForeignCountryRule";
    }

    @Override
    public FraudCheckResult apply(Transaction transaction) {
        if (!TRUSTED_COUNTRIES.contains(transaction.getCountry())) {
            return new FraudCheckResult(getRuleName(), true, "Transaction from untrusted country: " + transaction.getCountry());
        }
        return new FraudCheckResult(getRuleName(), false, "");
    }
}
