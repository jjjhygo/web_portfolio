package com.iot.pf.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iot.pf.dto.Board;

public interface BoardService {
	public ArrayList<Board> b_list();
	
	public int insert(Board board)throws Exception;
	
	public int count();
	public ArrayList<Board> paging(HashMap<String, Object> params);
	public int write(Board board)throws Exception;
	public Board readArticle(int seq)throws Exception;
	public boolean comparePw(String userId, String pw, String pass);
	public void delete(HashMap<String, String> params)throws Exception;
	public Board findById(int seq);
	public void update(Board board, List<MultipartFile> files)throws Exception;
	
	public void updateTest(Board board) throws Exception;
	
	public void writeTest(Board board, File[] files);
	/**
	 * 게시글 등록(첨부파일 포함)
	 * @param board 게시글
	 * @param files 첨부파일
	 */
	public void writeWithFile(Board board, List<MultipartFile> files);
	
	public int delAttachedFile(int attachSeq)throws Exception;
	
}
