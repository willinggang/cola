package com.willing.cargo.test;

import com.alibaba.cola.dto.Response;
import com.willing.base.util.WillingBeanUtils;
import com.willing.cargo.api.CustomerServiceI;
import com.willing.cargo.dto.CustomerAddCmd;
import com.willing.cargo.dto.data.CustomerDTO;
import com.willing.cargo.dto.data.ErrorCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is for integration test.
 * <p>
 * Created by fulan.zjf on 2017/11/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerServiceI customerService;


    @Before
    public void setUp() {

    }

    @Test
    public void testCustomerAddSuccess() {
        //1.prepare
        CustomerAddCmd customerAddCmd = new CustomerAddCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("NormalName");
        customerAddCmd.setCustomerDTO(customerDTO);

        //2.execute
        Response response = customerService.addCustomer(customerAddCmd);

        //3.assert
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testCustomerAddCompanyNameConflict() {
        //1.prepare
        CustomerAddCmd customerAddCmd = new CustomerAddCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("ConflictCompanyName");
        customerAddCmd.setCustomerDTO(customerDTO);

        //2.execute
        Response response = customerService.addCustomer(customerAddCmd);

        //3.assert error
        Assert.assertEquals(ErrorCode.B_CUSTOMER_companyNameConflict.getErrCode(), response.getErrCode());
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        int i = 0;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("yuirewjjfjdsifew");
        customerDTO.setCustomerName("rewurdskfjkdskj");
        customerDTO.setCustomerType("jfkdfjds");
        customerDTO.setSource("jfkdsjfkds");
        while (i < 1) {
            WillingBeanUtils.copy(customerDTO, CustomerDTO.class);
            i++;
        }
        start = System.currentTimeMillis() - start;
        System.out.println("1************ "+ start);
        start = System.currentTimeMillis();
        i = 0;
        while (i < 100) {
            WillingBeanUtils.copy(customerDTO, CustomerDTO.class);
            i++;
        }
        start = System.currentTimeMillis() - start;
        System.out.println("2************ "+ start);

        start = System.currentTimeMillis();
        i = 0;
        while (i < 100) {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(customerDTO,dto );
            i++;
        }
        start = System.currentTimeMillis() - start;
        System.out.println("3************ "+ start);
    }
}
