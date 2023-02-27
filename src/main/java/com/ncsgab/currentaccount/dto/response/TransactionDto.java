package com.ncsgab.currentaccount.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        String id,
        BigDecimal amount,
        AccountDto account,
        LocalDateTime transactionDate,
        String notes
) {
}
