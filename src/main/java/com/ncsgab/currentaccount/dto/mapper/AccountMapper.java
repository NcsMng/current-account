package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface AccountMapper {
    @Mapping(target = "creationDate", source = "creationTime")
    @Mapping(target = "transactions", qualifiedByName = "transactionToDtoWithoutFather")
    AccountDto entityToDto(Account entity);

    @Named("accountToDtoWithoutFather")
    @Mapping(target = "creationDate", source = "creationTime")
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "transactions", qualifiedByName = "transactionToDtoWithoutFather")
    AccountDto entityToDtoWithoutFather(Account entity);
}
