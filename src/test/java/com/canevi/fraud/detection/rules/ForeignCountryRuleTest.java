package com.canevi.fraud.detection.rules;

import com.canevi.fraud.detection.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ForeignCountryRuleTest {

    private final ForeignCountryRule rule = new ForeignCountryRule();

    @Test
    void shouldFlagForeignCountry() {
        var txn = new Transaction("1", "u1", new BigDecimal("100"), "USD", "CN", new Date());
        var result = rule.apply(txn);
        assertTrue(result.isFraud());
    }

    @Test
    void shouldAllowTrustedCountry() {
        var txn = new Transaction("2", "u1", new BigDecimal("100"), "USD", "US", new Date());
        var result = rule.apply(txn);
        assertFalse(result.isFraud());
    }
}

