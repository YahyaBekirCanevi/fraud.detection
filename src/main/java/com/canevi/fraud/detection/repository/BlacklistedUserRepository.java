package com.canevi.fraud.detection.repository;

import com.canevi.fraud.detection.entity.BlacklistedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedUserRepository extends JpaRepository<BlacklistedUser, String> {
    boolean existsByUserId(String userId);
}