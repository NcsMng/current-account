package com.ncsgab.currentaccount.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record AccountDto(
        String id,
        BigDecimal balance,
        LocalDateTime creationDate,
        CustomerDto owner,
        Set<TransactionDto> transactions
) {
}
