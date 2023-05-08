package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.mapper.CategoryMapper;
import com.xhxy.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

//	private CategoryMapper categoryMapper = MybatisUtils.getSqlSession().getMapper(CategoryMapper.class);
	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public Category findById(Integer id) {
		return categoryMapper.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryMapper.findAll();
	}

	@Override
	public List<Category> findTopCategory() {
		return categoryMapper.findTopCategory();
	}

}
