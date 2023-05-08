package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.*;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.mapper.CartMapper;
import com.xhxy.eshop.mapper.OrderItemMapper;
import com.xhxy.eshop.mapper.OrderMapper;
import com.xhxy.eshop.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

//	private OrderMapper orderMapper = MybatisUtils.getSqlSession().getMapper(OrderMapper.class);
//	private OrderItemMapper orderItemMapper = MybatisUtils.getSqlSession().getMapper(OrderItemMapper.class);
//	private CartMapper cartMapper = MybatisUtils.getSqlSession().getMapper(CartMapper.class);//等cartMapper完成后，启用
//	private CartItemMapper cartItemMapper = MybatisUtils.getSqlSession().getMapper(CartItemMapper.class);//等cartMapper完成后，启用
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderItemMapper orderItemMapper;
	@Resource
	private CartMapper cartMapper;
	@Resource
	private CartItemMapper cartItemMapper;

	@Override
	public Integer createOrder(Cart cart, Address address) {
		// 1.从用户id获取Cart
		//Cart cart = cartMapper.findByUserId(cartId);
		
		// 2.从Cart生成Order
		Order order = new Order();
		order.setStatus(Status.Completed);
		order.setAddress(address);
		order.setCreateTime(new Date());
		order.setTotal(cart.getTotal());
		order.setUser(cart.getUser());
		
		// 3.保存（插入）该Order，获得新插入的id值（自增长）
		Integer orderId =  orderMapper.save(order);
		
		order = orderMapper.findById(orderId);
		
		// 4.取得cart中的所有cartItem
		List<CartItem> cartItemList = cartItemMapper.findByCartId(cart.getId());
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		// 5.将cartItem转为orderItem，并放入List中
		for(CartItem cartItem:cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotal(cartItem.getTotal());
			
//			orderItemMapper.save(orderItem);	// 用for循环来批量插入
			orderItemList.add(orderItem);
		}
		
		// 6.保存（批量插入）cartItemList
		orderItemMapper.batchSave(orderItemList);	// 用mybatis的foreach批量插入
		
		// 7.清空购物车
		// 7.1. 先删除 该cart中的所有cartItem项
		cartItemMapper.deleteByCartId(cart.getId());
				
		// 7.2. 再将该cart的total项设为0
		cartMapper.updateTotal(cart.getId(), 0.0F);
		
		return orderId;
	}

	@Override
	public List<Order> getByUserId(Integer userId) {
		return orderMapper.getByUserId(userId); 
	}

	@Override
	public Order findById(Integer orderId) {
		return orderMapper.findById(orderId);
	}

}
