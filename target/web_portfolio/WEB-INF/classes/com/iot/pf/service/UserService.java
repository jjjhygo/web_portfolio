package com.iot.pf.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.iot.pf.dto.Board;
import com.iot.pf.dto.User;

public interface UserService {
	
	public ArrayList<User> list();
	public int join(User user) throws Exception;
	public int delete(int seq) throws Exception;
	
	public int update(User user) throws Exception;
	/**데이터수 가져오기
	 * 모든 데이터를 가져와서 totalArticle에 대입.
	 * @return
	 */
	public int count();
	/**회원가입
	 * 
	 * @param params 사용자가 입력한 값을 HashMap으로 받는다.
	 * @return
	 */
	public int register(HashMap<String, Object> params); 
	/**
	 * 중복된아이디 확인
	 * @param userId 사용자가 입력한 ID
	 * @return
	 */
	public int chkDuplication(String userId);
	public boolean comparePw(String pw, String id)throws Exception;
	public User getUser(String id);
	public User findUserBySeq(int seq);
	public ArrayList<User> jpaging(HashMap<String, String> params);
	public ArrayList<User> clPaging(HashMap<String, Object> params);
}
