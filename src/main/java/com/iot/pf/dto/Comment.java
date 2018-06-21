package com.iot.pf.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("Comment")
public class Comment {
	private int seq;
	private int userSeq;
	private String nickname;
	private int boardSeq;
	private String contents;
	private Date createDate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "Comment [seq=" + seq + ", userSeq=" + userSeq + ", nickname=" + nickname + ", boardSeq=" + boardSeq
				+ ", contents=" + contents + ", createDate=" + createDate + "]";
	}
}
