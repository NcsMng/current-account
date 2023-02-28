package com.ncsgab.currentaccount.service;

import com.ncsgab.currentaccount.dto.mapper.CustomerMapper;
import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.exception.CustomerNotFoundException;
import com.ncsgab.currentaccount.helpers.TestHelper;
import com.ncsgab.currentaccount.model.Customer;
import com.ncsgab.currentaccount.repository.CustomerRepository;
import com.ncsgab.currentaccount.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest extends TestHelper {

    private CustomerRepository customerRepository;
    private CustomerMapper converter;
    private CustomerService service;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerMapper.class);
        service = new CustomerServiceImpl(customerRepository, converter);
    }

    @Test
    public void testGetCustomerById_whenCustomerExists_shouldReturnCustomer() {
        Customer customer = customerSupplier.get();
        CustomerDto customerDto = new CustomerDto(0L, "customer-test-name", "customer-test-surname", localDateTimeSupplier.get(), Set.of());

        Mockito.when(customerRepository.findById(0L)).thenReturn(Optional.of(customer));
        Mockito.when(converter.entityToDto(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById(0L);

        assertEquals(result,
                customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById(-1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> service.getCustomerById(-1L));

        Mockito.verifyNoInteractions(converter);
    }
}