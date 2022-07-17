package com.willing.cargo.dto.query;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/317:31
 */
@Data
public class CargoByPhoneQry extends Query {
    /**
     * 手机号码
     */
    private String phone;
}
