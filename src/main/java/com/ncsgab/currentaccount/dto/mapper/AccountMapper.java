package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto entityToDto(Account entity);
}
