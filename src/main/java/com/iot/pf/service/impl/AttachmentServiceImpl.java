package com.iot.pf.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.pf.dao.AttachmentDao;
import com.iot.pf.dao.BoardDao;
import com.iot.pf.dto.Attachment;
import com.iot.pf.service.AttachmentService;
import com.iot.pf.util.FileUtil;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	AttachmentDao aDao;
	
	@Autowired
	BoardDao dao;
	
	@Autowired
	FileUtil fUtil;
	
	@Override
	public int insert(Attachment attach) {
		// TODO Auto-generated method stub
		return aDao.insert(attach);
	}

	@Override
	public ArrayList<Attachment> getAttachment(String docType, int seq) {
		// TODO Auto-generated method stub
		return aDao.getAttachment(docType, seq);
	}

	@Override
	public Attachment findById(int seq) {
	
		return aDao.findById(seq);
	}

	@Override
	@Transactional(rollbackFor= {Exception.class})
	public int deleteByid(int attachSeq) {
//		Attachment att = dao.findById(attachSeq);
		return aDao.deleteByid(attachSeq);
//		fUtil.delAttachedFile(att);
	}

}
