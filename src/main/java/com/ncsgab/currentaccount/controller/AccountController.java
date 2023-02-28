package com.ncsgab.currentaccount.controller;

import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.service.AccountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody NewAccountRequest request) {
        log.debug("NEW POST REQUEST TO /v1/account");
        AccountDto createdAccount = accountService.createAccount(request);
        log.debug("SENDING RESPONSE FROM /v1/account: {}", createdAccount);
        return ResponseEntity
                .ok(createdAccount);
    }
}
