package com.leyou.order.mapper;

import com.leyou.order.pojo.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
