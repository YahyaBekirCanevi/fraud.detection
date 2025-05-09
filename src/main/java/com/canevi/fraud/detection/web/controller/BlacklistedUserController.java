package com.canevi.fraud.detection.web.controller;

import com.canevi.fraud.detection.web.dto.BlacklistUserDTO;
import com.canevi.fraud.detection.service.BlacklistedUserService;
import com.canevi.fraud.detection.infrastructure.entity.BlacklistedUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blacklist")
@Tag(name = "Blacklist Management", description = "Manage blacklisted users")
@RequiredArgsConstructor
public class BlacklistedUserController {
    private final BlacklistedUserService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlacklistedUser> addUser(@RequestBody BlacklistUserDTO user) {
        return ResponseEntity.ok(service.addUser(user));
    }

    @GetMapping
    public ResponseEntity<List<BlacklistedUser>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> checkBlacklisted(@PathVariable String userId) {
        return ResponseEntity.ok(service.isBlacklisted(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        service.removeUser(userId);
        return ResponseEntity.noContent().build();
    }
}
