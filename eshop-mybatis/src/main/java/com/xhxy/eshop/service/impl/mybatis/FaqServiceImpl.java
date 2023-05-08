package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.mapper.FaqMapper;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.util.MybatisUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("faqService")
public class FaqServiceImpl implements FaqService {
	@Resource
	private FaqMapper faqMapper;
//	private FaqMapper faqMapper = MybatisUtils.getSqlSession().getMapper(FaqMapper.class);
	@Override
	public List<Faq> findAll(){
		return faqMapper.findAll();
	}

}
