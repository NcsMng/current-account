package com.ncsgab.currentaccount.helpers;

import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.model.Account;
import com.ncsgab.currentaccount.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@TestPropertySource(locations = "classpath:url.properties")
public class TestHelper {
    @Value("${customer.base-url}")
    protected String CUSTOMER_API_ENDPOINT;
    @Value("${account.base-url}")
    protected String ACCOUNT_API_ENDPOINT;
    public static final Supplier<LocalDateTime> localDateTimeSupplier =
            LocalDateTime::now;
    public static final Supplier<Customer> customerSupplier =
            () -> new Customer("customer-test-name", "customer-test-surname", new HashSet<>());
    public static final BiFunction<Long, Integer, NewAccountRequest> requestFromCustomerIdAndCredit =
            (Long customerId, Integer initialCredit) -> new NewAccountRequest(customerId, new BigDecimal(initialCredit));
    public static final Function<Integer, NewAccountRequest> requestFromInitialCredit =
            (Integer initialCredit) -> requestFromCustomerIdAndCredit.apply(0L, initialCredit);
    public static final Function<Integer, Account> accountFromBalance =
            (Integer balance) -> new Account(new BigDecimal(balance), customerSupplier.get(), new HashSet<>());

}
