package com.ncsgab.currentaccount.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public record CustomerDto(
        Long id,
        String name,
        String surname,
        LocalDateTime creationDate,
        Set<AccountDto> accounts
) {
}
