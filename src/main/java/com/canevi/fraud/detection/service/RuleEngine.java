package com.canevi.fraud.detection.service;

import com.canevi.fraud.detection.domain.model.FraudCheckResult;
import com.canevi.fraud.detection.domain.model.Transaction;
import com.canevi.fraud.detection.domain.rule.FraudRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RuleEngine {
    private final List<FraudRule> rules;

    public List<FraudCheckResult> evaluate(Transaction transaction) {
        return rules.stream().map(rule -> rule.apply(transaction)).filter(FraudCheckResult::isFraud).toList();
    }
}
