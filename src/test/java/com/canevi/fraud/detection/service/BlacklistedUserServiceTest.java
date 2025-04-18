package com.canevi.fraud.detection.service;

import com.canevi.fraud.detection.infrastructure.entity.BlacklistedUser;
import com.canevi.fraud.detection.infrastructure.repository.BlacklistedUserRepository;
import com.canevi.fraud.detection.web.dto.BlacklistUserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlacklistedUserServiceTest {

    @InjectMocks
    private BlacklistedUserService service;

    @Mock
    private BlacklistedUserRepository repository;

    private BlacklistedUser testUser;

    @BeforeEach
    void setup() {
        testUser = new BlacklistedUser();
        testUser.setUserId("fraudster001");
        testUser.setDescription("Suspicious activity");
        testUser.setCreatedAt(new Date());
    }

    @Test
    void shouldAddBlacklistedUser() {
        BlacklistUserDTO dto = new BlacklistUserDTO("fraudster001", "Suspicious activity");

        // Mock repository call
        when(repository.save(any(BlacklistedUser.class))).thenReturn(testUser);

        // Call service method
        BlacklistedUser addedUser = service.addUser(dto);

        // Verify the interaction and check the result
        verify(repository, times(1)).save(any(BlacklistedUser.class));
        assertNotNull(addedUser);
        assertEquals("fraudster001", addedUser.getUserId());
        assertEquals("Suspicious activity", addedUser.getDescription());
    }

    @Test
    void shouldReturnAllBlacklistedUsers() {
        // Mock repository call
        when(repository.findAll()).thenReturn(Collections.singletonList(testUser));

        // Call service method
        List<BlacklistedUser> users = service.getAll();

        // Verify the interaction and check the result
        verify(repository, times(1)).findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("fraudster001", users.getFirst().getUserId());
    }

    @Test
    void shouldCheckIfUserIsBlacklisted() {
        // Mock repository call
        when(repository.existsByUserId("fraudster001")).thenReturn(true);

        // Call service method
        boolean isBlacklisted = service.isBlacklisted("fraudster001");

        // Verify the result
        verify(repository, times(1)).existsByUserId("fraudster001");
        assertTrue(isBlacklisted);
    }

    @Test
    void shouldRemoveBlacklistedUser() {
        // Mock repository call
        doNothing().when(repository).deleteById("fraudster001");

        // Call service method
        service.removeUser("fraudster001");

        // Verify the interaction
        verify(repository, times(1)).deleteById("fraudster001");
    }

    @Test
    void shouldReturnFalseWhenUserNotBlacklisted() {
        // Mock repository call
        when(repository.existsByUserId("notexist")).thenReturn(false);

        // Call service method
        boolean isBlacklisted = service.isBlacklisted("notexist");

        // Verify the result
        verify(repository, times(1)).existsByUserId("notexist");
        assertFalse(isBlacklisted);
    }
}
