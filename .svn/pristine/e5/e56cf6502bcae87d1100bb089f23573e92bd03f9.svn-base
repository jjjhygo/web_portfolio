package com.iot.pf.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.iot.pf.dto.Friends;
import com.iot.pf.dto.Msg;

public interface MsgService {
	public int countSend(String userIdSend);
	public int countReceive(String userIdReceive);
	public ArrayList<Msg> paging(HashMap<String, Object> params);
	public ArrayList<Msg> pagingRe(HashMap<String, Object> params);
	public int write(Msg msg) throws Exception;
	public Msg getMsg(int seq);
	public int chkUpdate(Msg msg) throws Exception;
	public int makeFriend(Friends friend) throws Exception;
	public int count();
	public ArrayList<Friends> fPaging(HashMap<String, Object> params);
}
