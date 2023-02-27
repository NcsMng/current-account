package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.TransactionDto;
import com.ncsgab.currentaccount.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "transactionDate", source = "creationTime")
    TransactionDto entityToDto(Transaction entity);

    @Named("transactionToDtoWithoutFather")
    @Mapping(target = "transactionDate", source = "creationTime")
    @Mapping(target = "account", ignore = true)
    TransactionDto entityToDtoWithoutFather(Transaction entity);

}
