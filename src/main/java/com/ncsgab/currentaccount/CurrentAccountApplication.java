package com.ncsgab.currentaccount;

import com.ncsgab.currentaccount.repository.CustomerRepository;
import com.ncsgab.currentaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class CurrentAccountApplication implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public CurrentAccountApplication(CustomerRepository customerRepository, AccountService accountService) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrentAccountApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        Customer customer = customerRepository.save(new Customer("Mario", "Rossi", null));
//        Customer customer2 = customerRepository.save(new Customer("Luigi", "Verdi", null));
//        NewAccountRequest newAccountRequest = new NewAccountRequest(1L, new BigDecimal(1000));
//        AccountDto account = accountService.createAccount(newAccountRequest);
//        log.debug("Created customer 1 :{} \n With account: {}", customer, account);
//        log.debug("Created customer 2 :{}", customer2);
    }
}
