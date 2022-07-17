package com.willing.cargo.domain.customer.gateway;

import com.willing.cargo.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
