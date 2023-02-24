package com.ncsgab.currentaccount.service;

import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.dto.response.AccountDto;

public interface AccountService {
    AccountDto createAccount(NewAccountRequest createAccountRequest);

}
