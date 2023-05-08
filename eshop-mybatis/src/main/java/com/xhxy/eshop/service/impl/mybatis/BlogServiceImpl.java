package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.mapper.BlogMapper;
import com.xhxy.eshop.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

//	private BlogMapper blogMapper = MybatisUtils.getSqlSession().getMapper(BlogMapper.class);
	@Resource
	private BlogMapper blogMapper;
	@Override
	public List<Blog> findAll() {
		return blogMapper.findAll();
	}
	@Override
	public Blog findById(Integer id) {
		return blogMapper.findById(id);
	}
	@Override
	public List<Blog> findLatestBlog(Integer rows) {
		return blogMapper.findLatestBlog(rows);
	}
	@Override
	public List<Blog> findByPage(Page page) {
		return blogMapper.findByPage(page);
	}

}
