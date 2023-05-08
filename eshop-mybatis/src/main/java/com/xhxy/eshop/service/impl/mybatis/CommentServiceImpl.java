package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.mapper.CommentMapper;
import com.xhxy.eshop.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

//	private CommentMapper commentMapper = MybatisUtils.getSqlSession().getMapper(CommentMapper.class);
	@Resource
	private CommentMapper commentMapper;

	@Override
	public Integer save(Comment comment) {
		return commentMapper.save(comment);
	}

}
