package com.willing.cargo.web;

import com.alibaba.cola.dto.MultiResponse;
import com.willing.cargo.dto.data.CargoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Szg
 * @ClassName: CargoController
 * @Description: Cargo接口
 * @date 2022/7/317:12
 */
@RestController
@RequestMapping("/cargo")
public class CargoController {


    @RequestMapping(name = "list",method = RequestMethod.POST)
    public MultiResponse<CargoDTO> queryCargos(@RequestParam(value = "phone",required = false)String phone){
return null;
    }
}
