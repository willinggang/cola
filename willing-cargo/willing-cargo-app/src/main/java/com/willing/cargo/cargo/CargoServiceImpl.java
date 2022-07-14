package com.willing.cargo.cargo;

import com.alibaba.cola.dto.MultiResponse;
import com.willing.cargo.api.CargoServiceI;
import com.willing.cargo.dto.query.CargoByPhoneQry;
import com.willing.cargo.dto.data.CargoDTO;
import org.springframework.stereotype.Service;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/317:37
 */
@Service
public class CargoServiceImpl implements CargoServiceI {

    @Override
    public MultiResponse<CargoDTO> listCargos(CargoByPhoneQry query) {
        return null;
    }
}
