package com.xhxy.eshop.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.service.impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.OrderServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.AddressServiceImpl;
@WebServlet("/order")
public class OrderController extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = new CartServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	
	// 创建订单:create
	public String create(HttpServletRequest request, HttpServletResponse response) {
		int userId = (int)request.getSession().getAttribute("id");// 登录用户的id
		
		Cart cart = cartService.findByUserId(userId);
		request.setAttribute("cart", cart);
		
		List<Address> addressList = addressService.findByUserId(userId);
		request.setAttribute("addressList", addressList);
		
		return "order-create.jsp";
	}
	
	// 完成（结束）订单
	public String complete(HttpServletRequest request, HttpServletResponse response) {
		
		Integer id = (Integer)request.getSession().getAttribute("id");	// 即userId
		Cart cart = cartService.findByUserId(id);
		
		Integer addressId = Integer.parseInt( request.getParameter("addressId"));
		Address address = addressService.findById(addressId);
		
		orderService.createOrder(cart,address); 	
		
		return "/order-complete.jsp";
	}
}
