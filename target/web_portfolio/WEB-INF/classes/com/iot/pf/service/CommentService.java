package com.iot.pf.service;

import java.util.ArrayList;

import com.iot.pf.dto.Comment;
import com.iot.pf.exception.AnomalyException;

public interface CommentService {
	public int insert(Comment comment) throws AnomalyException;
	public ArrayList<Comment> commentList(int num);
	public int delete(int num) throws AnomalyException;
}
