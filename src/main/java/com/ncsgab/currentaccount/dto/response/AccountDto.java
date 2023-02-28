package com.ncsgab.currentaccount.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record AccountDto(
        Long id,
        BigDecimal balance,
        LocalDateTime creationDate,
        CustomerDto customer,
        Set<TransactionDto> transactions
) {
}
