package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.TransactionDto;
import com.ncsgab.currentaccount.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto entityToDto(Transaction entity);
}
