package com.iot.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.iot.pf.dto.User;

public interface UserDao {
	
	public ArrayList<User> list();
	public int join(User user);
	public int delete(int seq);
	
	public int update(User user);
	public int count();
	public int register(HashMap<String, Object> params); 
	public int chkDuplication(String userId);
	public String getEncryptedPw(String pw);
	public User getUser(String id);
	public User findUserBySeq(int seq);
	
	public ArrayList<User> jpaging(HashMap<String, String> params);
	public ArrayList<User> clPaging(HashMap<String, Object> params);
	
}