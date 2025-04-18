package com.canevi.fraud.detection.web.controller;

import com.canevi.fraud.detection.infrastructure.entity.BlacklistedUser;
import com.canevi.fraud.detection.service.BlacklistedUserService;
import com.canevi.fraud.detection.web.dto.BlacklistUserDTO;
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

import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BlacklistedUserControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private BlacklistedUserService blacklistedUserService;

    @InjectMocks
    private BlacklistedUserController controller;

    private BlacklistedUser testUser;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        testUser = new BlacklistedUser();
        testUser.setUserId("fraudster001");
        testUser.setDescription("Suspicious activity");
        testUser.setCreatedAt(new Date());
    }

    @Test
    void shouldAddBlacklistedUser() throws Exception {
        BlacklistUserDTO dto = new BlacklistUserDTO("fraudster001", "Suspicious activity");

        when(blacklistedUserService.addUser(any(BlacklistUserDTO.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/blacklist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("fraudster001"))
                .andExpect(jsonPath("$.description").value("Suspicious activity"));
    }

    @Test
    void shouldReturnAllBlacklistedUsers() throws Exception {
        when(blacklistedUserService.getAll()).thenReturn(Collections.singletonList(testUser));

        mockMvc.perform(get("/api/blacklist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].userId").value("fraudster001"));
    }

    @Test
    void shouldCheckIfUserIsBlacklisted() throws Exception {
        when(blacklistedUserService.isBlacklisted("fraudster001")).thenReturn(true);

        mockMvc.perform(get("/api/blacklist/fraudster001"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void shouldDeleteBlacklistedUser() throws Exception {
        doNothing().when(blacklistedUserService).removeUser("fraudster001");

        mockMvc.perform(delete("/api/blacklist/fraudster001"))
                .andExpect(status().isNoContent());
    }
}
