package com.canevi.fraud.detection.web.client;

import com.canevi.fraud.detection.web.dto.UserDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService", url = "${user.service.url}")
public interface UserServiceClient {

    @GetMapping("/api/user/detail/{userId}")
    UserDetailResponse getUserDetails(@PathVariable String userId);
}
