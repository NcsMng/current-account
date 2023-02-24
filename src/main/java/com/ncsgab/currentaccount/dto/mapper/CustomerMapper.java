package com.ncsgab.currentaccount.dto.mapper;

import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto entityToDto(Customer entity);
}
