package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.service.CartItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

//	private CartItemMapper cartItemMapper = MybatisUtils.getSqlSession().getMapper(CartItemMapper.class);
	@Resource
	private CartItemMapper cartItemMapper;

	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		return cartItemMapper.findByCartId(cartId);
	}

}
