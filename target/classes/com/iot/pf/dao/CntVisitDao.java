package com.iot.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.iot.pf.dto.CntVisit;

public interface CntVisitDao {
	
	public int insert(CntVisit visit);
	public int totalCount();
	public int todayCount();
	public ArrayList<CntVisit> visitPaging(HashMap<String, Object> params);
	public CntVisit getCntVisit(String userId);
}
