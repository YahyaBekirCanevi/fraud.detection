package com.canevi.fraud.detection.rules;

import com.canevi.fraud.detection.domain.FraudCheckResult;
import com.canevi.fraud.detection.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RapidFireTransactionRule implements FraudRule {
    private final Map<String, Date> lastTransactionMap = new ConcurrentHashMap<>();

    @Override
    public String getRuleName() {
        return "RapidFireTransactionRule";
    }

    @Override
    public FraudCheckResult apply(Transaction transaction) {
        Date now = transaction.getTimestamp();
        if(!lastTransactionMap.containsKey(transaction.getUserId())) {
            lastTransactionMap.put(transaction.getUserId(), now);
            return new FraudCheckResult(getRuleName(), false, "");
        }
        Date last = lastTransactionMap.get(transaction.getUserId());
        long difference = now.getTime() - last.getTime();
        last.setTime(last.getTime() + 10000L);

        lastTransactionMap.put(transaction.getUserId(), now);

        if (last.after(now)) {
            return new FraudCheckResult(getRuleName(), true, "Multiple transactions in short time ms:" + difference);
        }

        return new FraudCheckResult(getRuleName(), false, "");
    }
}