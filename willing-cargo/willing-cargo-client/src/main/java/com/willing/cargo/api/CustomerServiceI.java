package com.willing.cargo.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.willing.cargo.dto.CustomerAddCmd;
import com.willing.cargo.dto.CustomerListByNameQry;
import com.willing.cargo.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
