package com.ncsgab.currentaccount.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NewAccountRequest(@NotNull(message = "customerId is required") Long customerId,
                                @Min(value = 0, message = "Initial credit must be greater or equal to 0") BigDecimal initialCredit) {
}
