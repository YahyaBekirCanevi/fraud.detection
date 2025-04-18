package com.canevi.fraud.detection.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistedUser {
    @Id
    private String userId;
    @Column
    private String description;
    @Column
    private Date createdAt;
}