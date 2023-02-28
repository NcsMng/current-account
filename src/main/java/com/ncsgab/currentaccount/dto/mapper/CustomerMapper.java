package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface CustomerMapper {
    @Mapping(target = "creationDate", source = "creationTime")
    CustomerDto entityToDto(Customer entity);
}
