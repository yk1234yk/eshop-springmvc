package com.xhxy.eshop.controller;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 商品的控制器类
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Resource
	private ProductService productService;
//	private ProductService productService = new ProductServiceImpl();
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable(required = true) Integer id,
						 Model model){
		// 获取请求参数
		Product product = productService.findById(id);

		if(product != null) {
			model.addAttribute("product", product);
			return "product";
		}else {
			return "404";
		}
		
		
	}

}
