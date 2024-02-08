package ru.sberbank.jd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Data;

/**
 * The type User dto.
 */
@Data
public class UserDto {

    private UUID id;
    private String login;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
