package com.willing.cargo.api;

import com.alibaba.cola.dto.MultiResponse;
import com.willing.cargo.dto.CargoListByPhoneQuery;
import com.willing.cargo.dto.data.CargoDTO;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/317:35
 */
public interface CargoServiceI {
    /**
     * 查询cargo列表
     *
     * @param query
     * @return
     */
    MultiResponse<CargoDTO> listCargos(CargoListByPhoneQuery query);
}
