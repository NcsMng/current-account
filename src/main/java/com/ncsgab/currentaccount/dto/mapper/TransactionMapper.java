package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.dto.response.TransactionDto;
import com.ncsgab.currentaccount.model.Transaction;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {
    @Mapping(target = "transactionDate", source = "creationTime")
    @Mapping(target = "account", ignore = true)
    TransactionDto entityToDto(Transaction entity);

    @AfterMapping
    default TransactionDto mapParent(@MappingTarget TransactionDto target, Transaction source) {
        AccountDto accountDto = new AccountDto(
                source.getAccount().getId(),
                source.getAccount().getBalance(),
                source.getAccount().getCreationTime(),
                null,
                null);
        return new TransactionDto(target.id(), target.amount(), accountDto, target.transactionDate(), target.notes());
    }
}
