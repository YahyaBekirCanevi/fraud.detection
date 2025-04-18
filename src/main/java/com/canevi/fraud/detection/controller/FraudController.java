package com.canevi.fraud.detection.controller;

import com.canevi.fraud.detection.domain.FraudCheckResult;
import com.canevi.fraud.detection.domain.Transaction;
import com.canevi.fraud.detection.engine.RuleEngine;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fraud")
@Tag(name = "Fraud Detection", description = "APIs to evaluate transactions for fraud")
public class FraudController {

    private final RuleEngine ruleEngine;

    public FraudController(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    @PostMapping("/check")
    @Operation(
            summary = "Check if a transaction is fraudulent",
            description = "Evaluates a transaction using the configured fraud rules. The request body example is loaded externally via OpenApiCustomiser."
    )
    public List<FraudCheckResult> checkTransaction(@RequestBody Transaction transaction) {
        return ruleEngine.evaluate(transaction);
    }
}