package com.xhxy.eshop.controller;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Controller
public class IndexController {

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	@Resource
	private CartService cartService;

	@GetMapping("index")
	public String index(Model model) throws IOException {
		
		// 获取全部的顶层分类:用于左侧菜单
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 获取10条的热销商品
		List<Product> hotProductList = productService.findHot(10);
		
		// 获取最多10条的新品
		List<Product> latestProductList = productService.findLatest(10);
		
		// 获取三篇推荐文章
		List<Blog> blogList = blogService.findLatestBlog(3);
		
		// 从properties属性文件中读取 总请求数、首页访问数、在线用户数、全部用户数
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/eshop.properties")));
		props.load(in);
		
		// 设置model属性
		model.addAttribute("topCategoryList", topCategoryList);
		model.addAttribute("hotProductList", hotProductList);
		model.addAttribute("latestProductList", latestProductList);
		model.addAttribute("blogList", blogList);

		model.addAttribute("allRequest", props.getProperty("allRequest"));
		model.addAttribute("indexRequest", props.getProperty("indexRequest"));
		model.addAttribute("onlineUser", props.getProperty("onlineUser"));
		model.addAttribute("allUser", props.getProperty("allUser"));
						
		return "index";
	}
}
