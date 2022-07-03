package com.willing.cargo.domain.customer.gateway;

import com.willing.cargo.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
