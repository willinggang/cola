package com.willing.cargo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.willing.cargo.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/2415:22
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {
}
