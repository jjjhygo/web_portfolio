package com.iot.pf.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.pf.dao.CommentDao;
import com.iot.pf.dto.Comment;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao cDao;
		
	@Override
	public int insert(Comment comment) throws AnomalyException {
		int result = cDao.insert(comment);
		if(result!=1) throw new AnomalyException(1, result);
		return result;
	}

	@Override
	public ArrayList<Comment> commentList(int num) {
		// TODO Auto-generated method stub
		return cDao.commentList(num);
	}

	@Override
	public int delete(int num) throws AnomalyException {
		int result = cDao.delete(num);
		if(result!=1) throw new AnomalyException(1, result);
		return result;
	}

}
