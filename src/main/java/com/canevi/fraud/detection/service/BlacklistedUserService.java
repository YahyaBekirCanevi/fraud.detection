package com.canevi.fraud.detection.service;

import com.canevi.fraud.detection.web.dto.BlacklistUserDTO;
import com.canevi.fraud.detection.infrastructure.entity.BlacklistedUser;
import com.canevi.fraud.detection.infrastructure.repository.BlacklistedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlacklistedUserService {
    private final BlacklistedUserRepository repository;

    public BlacklistedUser addUser(BlacklistUserDTO user) {
        return repository.save(user.mapToEntity(new Date()));
    }

    public List<BlacklistedUser> getAll() {
        return repository.findAll();
    }

    public boolean isBlacklisted(String userId) {
        return repository.existsByUserId(userId);
    }

    public void removeUser(String userId) {
        repository.deleteById(userId);
    }
}
