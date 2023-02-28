package com.ncsgab.currentaccount.service;

import com.ncsgab.currentaccount.dto.mapper.AccountMapper;
import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.dto.response.TransactionDto;
import com.ncsgab.currentaccount.exception.CustomerNotFoundException;
import com.ncsgab.currentaccount.helpers.TestHelper;
import com.ncsgab.currentaccount.model.Account;
import com.ncsgab.currentaccount.model.Transaction;
import com.ncsgab.currentaccount.repository.AccountRepository;
import com.ncsgab.currentaccount.repository.CustomerRepository;
import com.ncsgab.currentaccount.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountServiceTest extends TestHelper {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private AccountMapper converter;
    private AccountService service;
    private final CustomerDto customerDto = new CustomerDto(0L, "customer-name", "customer-surname", localDateTimeSupplier.get(), new HashSet<>());

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        customerRepository = mock(CustomerRepository.class);
        converter = mock(AccountMapper.class);
        service = new AccountServiceImpl(customerRepository, converter, accountRepository);
    }

    @Test
    public void testCreateAccount_whenCustomerExistsAndInitialCreditGreaterThanZero_shouldCreateAccountWithTransaction() {

        NewAccountRequest request = requestFromInitialCredit.apply(1_000);

        Account account = accountFromBalance.apply(1_000);
        Transaction transaction = new Transaction(request.initialCredit(), account);
        account.getTransactions().add(transaction);
        AccountDto expected = new AccountDto(0L, new BigDecimal(1_000), localDateTimeSupplier.get(), customerDto, new HashSet<>());

        TransactionDto transactionDto = new TransactionDto("", new BigDecimal(1_000), expected, LocalDateTime.now(), "");
        expected.transactions().add(transactionDto);

        when(customerRepository.findById(0L)).thenReturn(Optional.of(customerSupplier.get()));
        when(accountRepository.save(account)).thenReturn(account);
        when(converter.entityToDto(account)).thenReturn(expected);

        AccountDto result = service.createAccount(request);

        assertEquals(result, expected);
    }

    @Test
    public void testCreateAccount_whenCustomerExistsAndInitialCreditIsZero_shouldCreateAccountWithoutTransaction() {
        NewAccountRequest request = requestFromInitialCredit.apply(0);

        Account account = accountFromBalance.apply(0);
        AccountDto expected = new AccountDto(0L, BigDecimal.ZERO, localDateTimeSupplier.get(), customerDto, Set.of());

        when(customerRepository.findById(0L)).thenReturn(Optional.of(customerSupplier.get()));
        when(accountRepository.save(account)).thenReturn(account);
        when(converter.entityToDto(account)).thenReturn(expected);

        AccountDto result = service.createAccount(request);

        assertEquals(result, expected);
    }

    @Test
    public void testCreateAccount_whenCustomerNotFound_shouldThrowCustomerNotFoundException() {
        NewAccountRequest request = requestFromInitialCredit.apply(0);

        when(customerRepository.findById(-1L)).thenThrow(new CustomerNotFoundException("test-exception"));

        assertThrows(CustomerNotFoundException.class,
                () -> service.createAccount(request));

        verify(customerRepository).findById(request.customerId());
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(converter);
    }
}