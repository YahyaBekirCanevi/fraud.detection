package com.canevi.fraud.detection.web.controller;

import com.canevi.fraud.detection.domain.model.Transaction;
import com.canevi.fraud.detection.domain.rule.FraudRule;
import com.canevi.fraud.detection.domain.rule.impl.BlacklistedUserRule;
import com.canevi.fraud.detection.domain.rule.impl.ForeignCountryRule;
import com.canevi.fraud.detection.domain.rule.impl.HighAmountRule;
import com.canevi.fraud.detection.domain.rule.impl.RapidFireTransactionRule;
import com.canevi.fraud.detection.infrastructure.repository.BlacklistedUserRepository;
import com.canevi.fraud.detection.service.RuleEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FraudControllerTest {

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        // Instantiate rules manually
        var mockBlacklistedUserRepository = mock(BlacklistedUserRepository.class);
        when(mockBlacklistedUserRepository.existsByUserId(anyString())).thenReturn(true);
        List<FraudRule> rules = List.of(
                new ForeignCountryRule(),
                new HighAmountRule(),
                new RapidFireTransactionRule(),
                new BlacklistedUserRule(mockBlacklistedUserRepository) // Inline mocked repo
        );

        RuleEngine ruleEngine = new RuleEngine(rules);
        FraudController fraudController = new FraudController(ruleEngine);
        mockMvc = MockMvcBuilders.standaloneSetup(fraudController).build();
    }

    @Test
    void shouldDetectFraudulentTransaction() throws Exception {
        Transaction txn = new Transaction(
                "txn-test-001",
                "fraudster001",
                new BigDecimal("15000"),
                "USD",
                "RU",
                new Date()
        );

        mockMvc.perform(post("/api/fraud/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(txn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3)) // should match 3 rules: amount, blacklisted, country
                .andExpect(jsonPath("$[0].ruleName").exists());
    }

}
