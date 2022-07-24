package com.willing.cargo.dao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.willing.cargo.mapper.OrderMapper;
import com.willing.cargo.dao.service.OrderService;
import com.willing.cargo.order.OrderDO;
import org.springframework.stereotype.Service;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/2415:22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {
}
