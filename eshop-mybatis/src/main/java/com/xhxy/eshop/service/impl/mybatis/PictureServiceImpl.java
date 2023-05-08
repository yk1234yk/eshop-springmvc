package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.Picture;
import com.xhxy.eshop.mapper.PictureMapper;
import com.xhxy.eshop.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {

//	private PictureMapper pictureMapper = MybatisUtils.getSqlSession().getMapper(PictureMapper.class);
	@Resource
	private PictureMapper pictureMapper;

	@Override
	public List<Picture> findByProductId(Integer productId) {
		return pictureMapper.findByProductId(productId);
	}

}
