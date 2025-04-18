package com.canevi.fraud.detection.rules;

import com.canevi.fraud.detection.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HighAmountRuleTest {

    private final HighAmountRule rule = new HighAmountRule();

    @Test
    void shouldFlagHighAmountTransaction() {
        Transaction txn = new Transaction("1", "u1", new BigDecimal("20000"), "USD", "US", new Date());
        var result = rule.apply(txn);
        assertTrue(result.isFraud());
        assertEquals("HighAmountRule", result.getRuleName());
    }

    @Test
    void shouldPassLowAmountTransaction() {
        Transaction txn = new Transaction("2", "u1", new BigDecimal("100"), "USD", "US", new Date());
        var result = rule.apply(txn);
        assertFalse(result.isFraud());
    }
}