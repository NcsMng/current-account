package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.model.Account;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface AccountMapper {
    @Mapping(target = "creationDate", source = "creationTime")
    @Mapping(target = "customer", ignore = true)
    AccountDto entityToDto(Account entity);

    @AfterMapping
    default AccountDto mapParent(@MappingTarget AccountDto target, Account source) {
        CustomerDto owner = new CustomerDto(
                source.getCustomer().getId(),
                source.getCustomer().getName(),
                source.getCustomer().getSurname(),
                source.getCustomer().getCreationTime(),
                null);
        return new AccountDto(target.id(), target.balance(), target.creationDate(), owner, target.transactions());
    }
}
