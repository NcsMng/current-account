package com.ncsgab.currentaccount.dto.response;

import java.util.Set;

public record CustomerDto(
        Long id,
        String name,
        String surname,
        Set<AccountDto> accounts
) {
}
