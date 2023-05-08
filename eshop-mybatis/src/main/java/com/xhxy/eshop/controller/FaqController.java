package com.xhxy.eshop.controller;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/faq")
public class FaqController {

//	private FaqService faqService = new FaqServiceImpl();
	@Resource(name = "faqService")
	private FaqService faqService;

	@GetMapping("/index")
	public String index(Model module){
		List<Faq> faqs = faqService.findAll();
		
		module.addAttribute("faqs", faqs);
		return "faq";
	}
}
