package ru.sberbank.jd.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Account dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private UUID id;
    private String name;
}
