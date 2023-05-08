package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.mapper.OrderItemMapper;
import com.xhxy.eshop.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

//	private OrderItemMapper orderItemMapper = MybatisUtils.getSqlSession().getMapper(OrderItemMapper.class);
	@Resource
	private OrderItemMapper orderItemMapper;

	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {
		return orderItemMapper.findByOrderId(orderId);
	}

	@Override
	public Integer save(OrderItem orderItem) {
		return orderItemMapper.save(orderItem);
	}

}
