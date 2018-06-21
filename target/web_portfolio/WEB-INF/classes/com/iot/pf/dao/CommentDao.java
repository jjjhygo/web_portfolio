package com.iot.pf.dao;

import java.util.ArrayList;

import com.iot.pf.dto.Comment;

public interface CommentDao {
	public int insert(Comment comment);
	public ArrayList<Comment> commentList(int num);
	public int delete(int num);
}
