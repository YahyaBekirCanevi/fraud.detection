package com.canevi.fraud.detection.web.dto;

public record UserDetailResponse(
        String userId,
        String name,
        String email,
        String role
) {}
