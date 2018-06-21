package com.iot.pf.dto;

import org.apache.ibatis.type.Alias;

@Alias("Friends")
public class Friends {
	private String myUserId;
	private String friendUserId;
	private String friendUserName;
	
	public String getMyUserId() {
		return myUserId;
	}
	public void setMyUserId(String myUserId) {
		this.myUserId = myUserId;
	}
	public String getFriendUserId() {
		return friendUserId;
	}
	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}
	public String getFriendUserName() {
		return friendUserName;
	}
	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}
	
	@Override
	public String toString() {
		return "Friends [myUserId=" + myUserId + ", friendUserId=" + friendUserId + ", friendUserName=" + friendUserName
				+ "]";
	}
	
}
