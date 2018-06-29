package com.iot.pf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.pf.dao.MsgDao;
import com.iot.pf.dto.Friends;
import com.iot.pf.dto.Msg;
import com.iot.pf.service.MsgService;
@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	MsgDao mDao;

	@Override
	public ArrayList<Msg> paging(HashMap<String, Object> params) {
		
		return mDao.paging(params);
	}
	@Override
	public int write(Msg msg) throws Exception {
		int result = mDao.write(msg);
		if(result!=1) throw new Exception();
		return result;
	}
	@Override
	public ArrayList<Msg> pagingRe(HashMap<String, Object> params) {
		
		return mDao.pagingRe(params);
	}
	@Override
	public Msg getMsg(int seq) {
		
		return mDao.getMsg(seq);
	}
	@Override
	public int chkUpdate(Msg msg) throws Exception {
		int result = mDao.chkUpdate(msg);
		if(result!=1) throw new Exception();
		return result;
	}
	@Override
	public int countSend(String userIdSend) {
		
		return mDao.countSend(userIdSend);
	}
	@Override
	public int countReceive(String userIdReceive) {
		
		return mDao.countReceive(userIdReceive);
	}
	@Override
	public int makeFriend(Friends friend) throws Exception {
		int result = mDao.makeFriend(friend);
		System.out.println(result);
		if(result!=1)throw new Exception();
		return result;
	}
	@Override
	public int count() {
		
		return mDao.count();
	}
	@Override
	public ArrayList<Friends> fPaging(HashMap<String, Object> params) {
		
		return mDao.fPaging(params);
	}

}
