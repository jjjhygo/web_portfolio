package com.iot.pf.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iot.pf.Controller.BoardController;
import com.iot.pf.dao.AttachmentDao;
import com.iot.pf.dao.BoardDao;
import com.iot.pf.dto.Attachment;
import com.iot.pf.dto.Board;
import com.iot.pf.dto.User;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.BoardService;
import com.iot.pf.util.FileUtil;

@Service
public class BoardServiceImpl implements BoardService {
	private Logger log = Logger.getLogger(BoardController.class);

	@Value("${file.upload.directory}")
	private String fileUploadDirectory;
	
	@Autowired
	FileUtil fUtil;

	@Autowired
	BoardDao dao;

	@Autowired
	AttachmentDao aDao;

	@Override
	public ArrayList<Board> b_list() {
		// TODO Auto-generated method stub
		return dao.b_list();
	}

	@Override
	public int insert(Board board) throws Exception {
		int result = dao.insert(board);
		if(result!=1)throw new AnomalyException(1, result);
		return result;
	}


	@Override
	public int count() {

		return dao.count();
	}

	@Override
	public ArrayList<Board> paging(HashMap<String, Object> params) {

		return dao.paging(params);
	}

	@Override//writeWithFile메서드 때문에 이건 필요없다.
	public int write(Board board)throws Exception { 
		int result=dao.write(board);
		if(result!=1)throw new Exception("글쓰기 오류");

		return result;
	}

	@Override
	public Board readArticle(int seq)throws Exception {

		int result = dao.updatehits(seq);
		if(result!=1)throw new Exception("UPDATE_HITS_ERROR");

		return dao.findById(seq);
	}

	@Override
	public boolean comparePw(String userId, String pw, String pass){
		if(pass.equals("true")) {
			return true;
		}
		else {
		String getEnPw = dao.getEncryptedPw(pw);
		User user = dao.getUser(userId);
		
		//		String userEncryptedPw=dao.getEncryptedPw(user.getUserPw());
		return getEnPw.equals(user.getUserPw());
		}
	}

	@Override
	public void delete(HashMap<String, String> params) throws Exception {
		int seq = Integer.parseInt(params.get("num"));
		ArrayList<Attachment> attaches = aDao.getAttachment("free", seq);

		int result= dao.delete(seq);//게시글 삭제
		if(result!=1)throw new Exception("DELETE_ERROR");

		for(Attachment att : attaches) {
			aDao.deleteByid(att.getAttachSeq());//첨부파일 삭제
			
			File f = new File(fileUploadDirectory, att.getFakeName());
//			File f = new File("c:/tmp/upload", att.getFakeName());
//			File f = new File("/home/ubuntu/app/upload/pf", att.getFakeName());
			f.delete();//물리저장소에서 삭제
		}

	}

	@Override
	public Board findById(int seq) {
		// TODO Auto-generated method stub
		return dao.findById(seq);
	}

	@Override
	@Transactional(rollbackFor= {Exception.class})
	public void update(Board board, List<MultipartFile> files) throws Exception {
		int result = dao.update(board); //Board DB 업데이트
		if(result!=1)throw new AnomalyException(1, result);

		int seq =board.getNum();//글 번호 꺼내기
		if(files != null) {
			for(MultipartFile f : files) {
				if(!f.isEmpty()) {
					Attachment att = new Attachment();
					att.setAttachDocSeq(seq);
					att.setAttachDocType("free");
					att.setFilename(f.getOriginalFilename());
					att.setFileSize(f.getSize());
					String fakeName = UUID.randomUUID().toString();
					att.setFakeName(fakeName);

					aDao.insert(att); //attachment DB에 insert

					fUtil.copyToFoler(f, fakeName);//물리저장소에 저장
				}
			}
		}
	}
	@Override
	public void writeTest(Board board, File[] files) {
		//게시글 먼저 입력
		dao.write(board);
		int num = board.getNum(); //글 번호 꺼내기
		for(File f : files) {
			Attachment att = new Attachment();
			att.setAttachDocSeq(num);
			att.setAttachDocType("free");
			att.setFilename(f.getName());
			att.setFileSize(f.length());
			String fakeName = UUID.randomUUID().toString();
			att.setFakeName(fakeName);

			aDao.insert(att);


			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				File target = new File(fileUploadDirectory);
				if(!target.exists())
					target.mkdirs();
				target= new File(target, fakeName);
				FileOutputStream fos = new FileOutputStream(target);

				int data = 0;
				while((data=fis.read()) != -1) {
					fos.write(data);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	@Transactional(rollbackFor= {Exception.class})
	public void writeWithFile(Board board, List<MultipartFile> files) {
		//게시글 먼저 입력
		dao.write(board);
		int num = board.getNum();//글 번호 꺼내기
		for(MultipartFile f : files) {
			if(!f.isEmpty()) {
				Attachment att = new Attachment();
				att.setAttachDocSeq(num);
				att.setAttachDocType("free");
				att.setFilename(f.getOriginalFilename());
				att.setFileSize(f.getSize());
				String fakeName = UUID.randomUUID().toString();
				att.setFakeName(fakeName);

				aDao.insert(att);//attachment DB에 저장
				fUtil.copyToFoler(f, fakeName);
			}
		}
	}

	@Override
	@Transactional(rollbackFor= {Exception.class})
	public int delAttachedFile(int attachSeq) throws Exception {
		Attachment att = aDao.findById(attachSeq);//첨부파일 정보 가져오기
		int seq = att.getAttachDocSeq(); //게시글 번호 가져오기
		Board board = dao.findById(seq);//게시글 번호로 해당 게시글 정보 가져오기

		aDao.deleteByid(attachSeq);//첨부파일 삭제(DB)

		ArrayList<Attachment> remain = aDao.getAttachment("free", seq);//남은 첨부파일 정보 조회
		System.out.println("asdasd" + remain.size());
		if(remain.size()==0) {//남은 첨부파일이 없다면
			board.setHasFile("0");//hasFile값을 0으로 변경
			dao.update(board);//변경된 값으로 DB update
		}
		File f = new File(fileUploadDirectory, att.getFakeName()); 
//		File f = new File("c:/tmp/upload", att.getFakeName()); 
//		File f = new File("/home/ubuntu/app/upload/pf", att.getFakeName()); 
			f.delete();//물리저장소에서 삭제
		
		return seq;
	}

	@Override
	public void updateTest(Board board) {
		dao.updateTest(board);
		
	}


	
}


