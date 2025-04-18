package com.canevi.fraud.detection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BlacklistedUser {
    @Id
    private String userId;
}