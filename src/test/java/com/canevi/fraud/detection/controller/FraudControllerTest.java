package com.canevi.fraud.detection.controller;

import com.canevi.fraud.detection.domain.Transaction;
import com.canevi.fraud.detection.engine.RuleEngine;
import com.canevi.fraud.detection.repository.BlacklistedUserRepository;
import com.canevi.fraud.detection.rules.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
