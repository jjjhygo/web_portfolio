package com.iot.pf.dto;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
@Alias("CntVisit")
public class CntVisit {
	private int seq;
	private String userId;
	private Timestamp visitDate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Timestamp visitDate) {
		this.visitDate = visitDate;
	}
	
	@Override
	public String toString() {
		return "CntVisit [seq=" + seq + ", userId=" + userId + ", visitDate=" + visitDate + "]";
	}
}
