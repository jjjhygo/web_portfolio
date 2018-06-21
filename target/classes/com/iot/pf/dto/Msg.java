package com.iot.pf.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("Msg")
public class Msg {
	private int msgSeq;
	private int receiveMsgSeq;
	private int sendMsgSeq;
	private String userIdReceive;
	private String userIdSend;
	private String msgTitle;
	private String msgContents;
	private Timestamp sendDate;
	private String msgCheck;
	
	public int getMsgSeq() {
		return msgSeq;
	}
	public void setMsgSeq(int msgSeq) {
		this.msgSeq = msgSeq;
	}
	public int getReceiveMsgSeq() {
		return receiveMsgSeq;
	}
	public void setReceiveMsgSeq(int receiveMsgSeq) {
		this.receiveMsgSeq = receiveMsgSeq;
	}
	public int getSendMsgSeq() {
		return sendMsgSeq;
	}
	public void setSendMsgSeq(int sendMsgSeq) {
		this.sendMsgSeq = sendMsgSeq;
	}
	public String getUserIdReceive() {
		return userIdReceive;
	}
	public void setUserIdReceive(String userIdReceive) {
		this.userIdReceive = userIdReceive;
	}
	public String getUserIdSend() {
		return userIdSend;
	}
	public void setUserIdSend(String userIdSend) {
		this.userIdSend = userIdSend;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContents() {
		return msgContents;
	}
	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}
	public Timestamp getSendDate() {
		return sendDate;
	}
	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}
	public String getMsgCheck() {
		return msgCheck;
	}
	public void setMsgCheck(String msgCheck) {
		this.msgCheck = msgCheck;
	}
	@Override
	public String toString() {
		return "Msg [msgSeq=" + msgSeq + ", receiveMsgSeq=" + receiveMsgSeq + ", sendMsgSeq=" + sendMsgSeq
				+ ", userIdReceive=" + userIdReceive + ", userIdSend=" + userIdSend + ", msgTitle=" + msgTitle
				+ ", msgContents=" + msgContents + ", sendDate=" + sendDate + ", msgCheck=" + msgCheck + "]";
	}
}
