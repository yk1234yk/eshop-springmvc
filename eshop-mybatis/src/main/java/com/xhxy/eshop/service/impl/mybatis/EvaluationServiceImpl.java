package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.Evaluation;
import com.xhxy.eshop.mapper.EvaluationMapper;
import com.xhxy.eshop.service.EvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService {

//	private EvaluationMapper evaluationMapper = MybatisUtils.getSqlSession().getMapper(EvaluationMapper.class);
	@Resource
	private EvaluationMapper evaluationMapper;

	@Override
	public List<Evaluation> findByProductId(Integer productId) {
		return evaluationMapper.findByProductId(productId);
	}

}
