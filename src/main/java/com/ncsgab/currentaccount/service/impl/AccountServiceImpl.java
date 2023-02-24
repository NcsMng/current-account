package com.ncsgab.currentaccount.service.impl;

import com.ncsgab.currentaccount.dto.mapper.AccountMapper;
import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.exception.CustomerNotFoundException;
import com.ncsgab.currentaccount.model.Account;
import com.ncsgab.currentaccount.model.Transaction;
import com.ncsgab.currentaccount.repository.AccountRepository;
import com.ncsgab.currentaccount.repository.CustomerRepository;
import com.ncsgab.currentaccount.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(CustomerRepository customerRepository, AccountMapper accountMapper, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(NewAccountRequest createAccountRequest) {
        return customerRepository.findById(createAccountRequest.customerId())
                .map(customer -> {
                    BigDecimal initialCredit = createAccountRequest.initialCredit();

                    Account account = Account.builder()
                            .balance(initialCredit)
                            .customer(customer)
                            .build();

                    if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
                        Transaction transaction = Transaction.builder()
                                .account(account)
                                .amount(initialCredit)
                                .build();
                        account.getTransactions().add(transaction);
                    }
                    return account;
                })
                .map(accountRepository::save)
                .map(accountMapper::entityToDto)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot create account for user %s: user does not exits!", createAccountRequest.customerId())));
    }
}
