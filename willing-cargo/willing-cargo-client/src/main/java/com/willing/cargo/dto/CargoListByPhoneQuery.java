package com.willing.cargo.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/317:31
 */
@Data
public class CargoListByPhoneQuery extends Query {
    /**
     * 手机号码
     */
    private String phone;
}
