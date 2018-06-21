package com.iot.pf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.pf.dao.UserDao;
import com.iot.pf.dto.Board;
import com.iot.pf.dto.User;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.UserService;
@Service("userService")
@Transactional(rollbackFor= {Exception.class})
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public ArrayList<User> list() {
		
		return dao.list();
	}

	@Override
	public int join(User user)throws Exception {
		int result = dao.join(user);
		result = 0;
		if(result!=1) throw new AnomalyException(1, result);
		return result;
	}

	@Override
	public int delete(int seq) throws Exception {
		int result = dao.delete(seq);
		if(result!=1) throw new AnomalyException(1,result);//사용자 정의 Exception 
		return result;
	}
	
	@Override
	public int update(User user) throws Exception {
		int result = dao.update(user);
		if(result!=1) throw new AnomalyException(1, result);
		
		return result;
	}

	
	
	@Override
	public int count() {
		
		return dao.count();
	}
	
	@Override
	public int register(HashMap<String, Object> params) {
		
		return dao.register(params);
	}

	
	@Override
	public int chkDuplication(String userId) {
		
		return dao.chkDuplication(userId);
	}

	@Override
	public boolean comparePw(String pw, String id)throws Exception {
		int result=dao.chkDuplication(id);
		if(result!=1) throw new Exception("NOT_FOUND_USERID");
		User getUser = dao.getUser(id);
		String getPw = dao.getEncryptedPw(pw);
		return getPw.equals(getUser.getUserPw());
	}

	@Override
	public User getUser(String id) {
		
		return dao.getUser(id);
	}

	@Override
	public ArrayList<User> jpaging(HashMap<String, String> params) {
		
		return dao.jpaging(params);
	}

	@Override
	public User findUserBySeq(int seq) {
		
		return dao.findUserBySeq(seq);
	}

	@Override
	public ArrayList<User> clPaging(HashMap<String, Object> params) {
		
		return dao.clPaging(params);
	}

}
