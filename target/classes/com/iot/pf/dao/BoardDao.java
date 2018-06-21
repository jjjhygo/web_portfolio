package com.iot.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.iot.pf.dto.Board;
import com.iot.pf.dto.User;

public interface BoardDao {
	public ArrayList<Board> b_list();
	
	public int insert(Board board);
	
	public int count();
	public ArrayList<Board> paging(HashMap<String, Object> params);
	public int write(Board board);
	public int updatehits(int seq);
	public Board findById(int seq);
	//password 암호화
	public String getEncryptedPw(String pw);
	//iot.user에서 가져온 User객체
	public User getUser(String userId);
	public int delete(int seq);
	public int update(Board board);
	public void updateTest(Board board);
}
