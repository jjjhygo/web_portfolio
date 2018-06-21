package com.iot.pf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.pf.dao.CntVisitDao;
import com.iot.pf.dto.CntVisit;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.CntVisitService;

@Service
public class CntVisitServiceImpl implements CntVisitService {

	@Autowired
	CntVisitDao cvDao;
	
	@Override
	public int insert(CntVisit visit)throws Exception {
		int result = cvDao.insert(visit);
		if(result!=1)throw new Exception();
		return result;
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return cvDao.totalCount();
	}

	@Override
	public int todayCount() {
		// TODO Auto-generated method stub
		return cvDao.todayCount();
	}

	@Override
	public ArrayList<CntVisit> visitPaging(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return cvDao.visitPaging(params);
	}

	@Override
	public CntVisit getCntVisit(String userId) {
		// TODO Auto-generated method stub
		return cvDao.getCntVisit(userId);
	}

}
