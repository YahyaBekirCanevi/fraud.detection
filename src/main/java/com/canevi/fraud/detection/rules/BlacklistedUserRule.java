package com.canevi.fraud.detection.rules;

import com.canevi.fraud.detection.domain.FraudCheckResult;
import com.canevi.fraud.detection.domain.Transaction;
import com.canevi.fraud.detection.repository.BlacklistedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlacklistedUserRule implements FraudRule {
    private final BlacklistedUserRepository blacklistedUserRepository;

    @Override
    public String getRuleName() {
        return "BlacklistedUserRule";
    }

    @Override
    public FraudCheckResult apply(Transaction transaction) {
        if (blacklistedUserRepository.existsByUserId(transaction.getUserId())) {
            return new FraudCheckResult(getRuleName(), true, "User is blacklisted!");
        }
        return new FraudCheckResult(getRuleName(), false, "");
    }
}
