package com.canevi.fraud.detection.web.dto;

import com.canevi.fraud.detection.infrastructure.entity.BlacklistedUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Example to blacklist user")
public class BlacklistUserDTO {
    @Schema(description = "User ID to blacklist", example = "fraudster001")
    private String userId;

    @Schema(description = "Description of blacklisting", example = "Known fraudster from PSP alert")
    private String description;

    public BlacklistedUser mapToEntity(Date date) {
        return new BlacklistedUser(userId, description, date);
    }

    public BlacklistUserDTO mapToDTO(BlacklistedUser user) {
        return new BlacklistUserDTO(user.getUserId(), user.getDescription());
    }
}
