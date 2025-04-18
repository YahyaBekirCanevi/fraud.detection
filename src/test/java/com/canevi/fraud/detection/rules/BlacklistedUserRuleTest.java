package com.canevi.fraud.detection.rules;

import com.canevi.fraud.detection.domain.Transaction;
import com.canevi.fraud.detection.repository.BlacklistedUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlacklistedUserRuleTest {

    @InjectMocks
    BlacklistedUserRule blacklistedUserRule;

    @Mock
    BlacklistedUserRepository blacklistedUserRepository;

    @Test
    void shouldFlagBlacklistedUser() {
        when(blacklistedUserRepository.existsByUserId("fraudUser")).thenReturn(true);

        var txn = new Transaction("1", "fraudUser", new BigDecimal("100"), "USD", "US", new Date());

        var result = blacklistedUserRule.apply(txn);
        assertTrue(result.isFraud());
    }

    @Test
    void shouldPassForCleanUser() {
        when(blacklistedUserRepository.existsByUserId("cleanUser")).thenReturn(false);

        var txn = new Transaction("2", "cleanUser", new BigDecimal("100"), "USD", "US", new Date());

        var result = blacklistedUserRule.apply(txn);
        assertFalse(result.isFraud());
    }
}
