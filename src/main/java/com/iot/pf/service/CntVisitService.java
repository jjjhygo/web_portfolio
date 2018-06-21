package com.iot.pf.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.iot.pf.dto.CntVisit;

public interface CntVisitService {
	public int insert(CntVisit visit)throws Exception;
	public int totalCount();
	public int todayCount();
	public ArrayList<CntVisit> visitPaging(HashMap<String, Object> params);
	public CntVisit getCntVisit(String userId);
}
