package com.iot.pf.Controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.iot.pf.dto.Attachment;
import com.iot.pf.dto.Board;
import com.iot.pf.dto.Comment;
import com.iot.pf.service.AttachmentService;
import com.iot.pf.service.BoardService;
import com.iot.pf.service.CommentService;
import com.iot.pf.util.FileUtil;

@Controller
public class BoardController {
	private Logger log = Logger.getLogger(BoardController.class);

	@Autowired
	CommentService cs;
	@Autowired
	BoardService bs;
	@Autowired
	AttachmentService as;
	@Autowired
	FileUtil fUtil;
	@RequestMapping("/bbs/free/list.do")
	public ModelAndView list(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		log.debug("/bbs/free/list.do - params : " + params);
		//게시글 페이지
		int totalArticle = bs.count(); //총 게시글 수
		System.out.println(totalArticle);
		int pageArticle = 10; //페이지당 게시글 수
		int currentPageNo = 1;
		if(params.containsKey("currentPageNo")) {
			currentPageNo = Integer.valueOf(params.get("currentPageNo"));
		}
		int totalPage =(totalArticle % pageArticle ==0 ? totalArticle/pageArticle : totalArticle/pageArticle +1);
		int startArticleNo = (currentPageNo-1)*pageArticle;

		int pageBlockSize = 10;
		int pageBlockStart = (currentPageNo-1)/pageBlockSize*pageBlockSize+1;
		int pageBlockEnd = (currentPageNo-1)/pageBlockSize*pageBlockSize+pageBlockSize;
		pageBlockEnd = (pageBlockEnd<=totalPage)?pageBlockEnd:totalPage;

		HashMap<String, Object> P = new HashMap<String, Object>();
		P.put("start", startArticleNo);
		P.put("pageArticle", pageArticle);
		P.put("searchType", params.get("searchType")); //검색 유형
		P.put("searchText", params.get("searchText")); //검색어

		ArrayList<Board> result = bs.paging(P);

		md.addObject("num", params.get("num"));
		md.addObject("userId" ,session.getAttribute("userId"));
		md.addObject("result", result);
		md.addObject("currentPageNo", currentPageNo);
		md.addObject("totalPage", totalPage);
		md.addObject("pageBlockSize", pageBlockSize);
		md.addObject("pageBlockEnd", pageBlockEnd);
		md.addObject("pageBlockStart", pageBlockStart);
		md.addObject("searchType", params.get("searchType"));
		md.addObject("searchText", params.get("searchText"));
		md.setViewName("/bbs/free/list3");
		return md;
	}

	@RequestMapping("/goWrite.do")
	public ModelAndView goWrite(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		if(session.getAttribute("userId")!=null) {//로그인 한다면
			md.addObject("num", params.get("num"));
			md.addObject("currentPageNo", params.get("currentPageNo"));
			md.setViewName("/bbs/free/write");
		}
		else {//로그인이 안된다면
			md.setViewName("error/error");
			md.addObject("nextLocation", "/login.do");
			md.addObject("msg", "로그인 도중 오류가 발생하였습니다.");
		}
		return md;
	}
	@RequestMapping("/write.do")                                                                 //파일을 업로드 하기 위한
	public ModelAndView write(@RequestParam HashMap<String, String> params, HttpSession session, MultipartHttpServletRequest mReq) {
		ModelAndView md = new ModelAndView();
		log.debug("/write.do - paramss : " + params);
		List<MultipartFile> mFiles = mReq.getFiles("attachFile");
		String hasFile = "0";
		for(MultipartFile f : mFiles) {
			hasFile = f.isEmpty() ? "0":"1";
			if(hasFile.equals("1"))break;
		}
		/*for(MultipartFile f : mFiles) {
			log.debug("getName() : " + f.getName());
			log.debug("getOriginalFilename() : "+ f.getOriginalFilename());
			log.debug("getContentType() : "+ f.getContentType());
		}*/
		Board board = new Board();
		board.setUserName(params.get("userName"));
		board.setUserId(params.get("userId"));
		board.setTitle(params.get("title"));
		board.setHasFile(hasFile);
		String contents = params.get("contents");
		contents = contents.replaceAll("\n", "<br>");
		board.setContents(contents);

		try {
			bs.writeWithFile(board, mFiles);
		} catch (Exception e) {
			e.getMessage().equals("파일 첨부 중 오류가 발생하였습니다.");
			e.printStackTrace();
			md.setViewName("bbs/free/write");
			return md;
		}


		RedirectView rv = new RedirectView("/web_portfolio/bbs/free/list.do");
		md.setView(rv);
		return md;
	}
	@RequestMapping("/bbs/free/readArticle.do")
	public ModelAndView readArticle(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		int seq = Integer.parseInt(params.get("num"));
		Board board = null;
		try {
			board=bs.readArticle(seq);
			if(board.getHasFile().equals("1")) {
				ArrayList<Attachment> aList = as.getAttachment("free", board.getNum());
				md.addObject("attachments", aList);

			}
		} catch (Exception e) {
			e.printStackTrace();
			switch(e.getMessage()) {
			case "UPDATE_HITS_ERROR":
				md.addObject("msg", "<font color=red><b>議고���� 利�媛� �ㅻ�</b></font>");
				RedirectView rv = new RedirectView("/web_portfolio/bbs/free/list.do");
			}
		}
		ArrayList<Comment> comment = cs.commentList(seq);
		md.addObject("comment", comment);
		md.addObject("currentPageNo", params.get("currentPageNo"));
		md.addObject("msg", params.get("msg"));
		md.addObject("userId", session.getAttribute("userId"));
		md.addObject("board", board);
		md.setViewName("bbs/free/read");
		return md;
	}
	/*�쎄린 -> delete.do
		seq, password, userId
	1.password ���명��
	2.寃���湲� ���깆�� ��蹂대�� 媛��몄��
	3.(1) (2) 鍮�援�
	 */
	@RequestMapping("/delete.do")
	public ModelAndView delete(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		String pass = "";
		if(params.containsKey("pass"))
			pass = params.get("pass");

		Board board=bs.findById(Integer.valueOf(params.get("num")));
		System.out.println(board);
		String userId= board.getUserId();
		String pw = params.get("password");
		//로그인한 상태이고 로그인한 아이디와 작성자와 같다면
		if(session.getAttribute("userId")!=null&&session.getAttribute("userId").equals(params.get("userId"))) {
			//아이디와 비밀번호를 비교해서 맞다면
			if(bs.comparePw(userId, pw, pass)) {
				try {
					log.debug("paramss : "+params);
					bs.delete(params);
				} catch (Exception e) {
					e.printStackTrace();
					RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do?currentPageNo="+params.get("currentPageNo")+"&num="+params.get("num"));
					md.setView(rv);
					return md;
				}
				RedirectView rv = new RedirectView("/web_portfolio/bbs/free/list.do");
				md.setView(rv);
			}
			else {
				RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do?currentPageNo="+params.get("currentPageNo")+"&num="+params.get("num"));
				md.addObject("msg", "<font color=green><b>비밀번호가 틀렸습니다.</b></font>");
				md.setView(rv);
				return md;
			}
		}
		else {
			RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do?currentPageNo="+params.get("currentPageNo")+"&num="+params.get("num"));
			md.addObject("msg", "<font color=green><b>작정자가 같지 않습니다.</b></font>");
			md.setView(rv);
			return md;
		}
		md.addObject("userId", session.getAttribute("userId"));
		return md;
	}
	@RequestMapping("/goUpdate.do")
	public ModelAndView goUpdate(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		String pass = "";
		if(params.containsKey("pass"))
			pass = params.get("pass");
		
		String pw = params.get("password");
		String userId = params.get("userId");
		int seq = Integer.parseInt(params.get("num"));

		List<Attachment> attaches = null;

		if(!pass.equals("true")) {
			//글 작성자의 비밀번호가 맞다면
			if(bs.comparePw(userId, pw, pass)) {
				md.setViewName("bbs/free/update");
				Board board = bs.findById(seq); //해당 번호의 글 정보 가져오기
				if(board.getHasFile().equals("1")) {//첨부파일이 있다면
					attaches = as.getAttachment("free", board.getNum());
				}
				md.addObject("attachments", attaches);
				md.addObject("num", params.get("num"));
				md.addObject("board", board);
				md.addObject("currentPageNo", params.get("currentPageNo"));
				return md;
			}
			else {
				RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do?currentPageNo="+params.get("currentPageNo")+"&num="+params.get("num"));
				md.addObject("msg", "<font color=green><b>비밀번호가 틀렸습니다.</b></font>");
				md.setView(rv);
			}
		}
		else {
			md.setViewName("bbs/free/update");
			Board board = bs.findById(seq); 
			if(board.getHasFile().equals("1")) {
				attaches = as.getAttachment("free", board.getNum());
			}
			md.addObject("attachments", attaches);
			md.addObject("num", params.get("num"));
			md.addObject("board", board);
			md.addObject("currentPageNo", params.get("currentPageNo"));
			return md;
		}
		return md;
	}

	@RequestMapping("/update.do")
	public ModelAndView update(@RequestParam HashMap<String, String> params, HttpSession session, MultipartHttpServletRequest mReq) {
		ModelAndView md = new ModelAndView();
		int seq = Integer.parseInt(params.get("num"));
		Board board=bs.findById(seq);//해당 번호의 글 정보 가져오기

		List<MultipartFile> mFiles =null;
		String hasFile = "0";
		if(board.getHasFile().equals("0")) {
			mFiles=mReq.getFiles("attachFile");
			for(MultipartFile f : mFiles) {
				hasFile = !f.isEmpty() ? "1" : "0"; //첨부파일이 존재하면 1 없으면 0
				if(hasFile.equals("1")) break;
			}
		}
		board.setHasFile(hasFile);
		board.setUserName(params.get("userName"));
		board.setTitle(params.get("title"));
		String contents = params.get("contents");
		contents = contents.replaceAll("(!?)<br */?>", "\n");
		board.setContents(contents);
		try {
			bs.update(board, mFiles);
		} catch (Exception e) {
			e.printStackTrace();
			RedirectView rv = new RedirectView("/web_portfolio/goUpdate.do");
			md.setView(rv);
			return md;
		}
		RedirectView rv = new RedirectView("/web_portfolio/bbs/free/list.do?currentPageNo="+params.get("currentPageNo"));
		md.setView(rv);
		md.addObject("board", board);
		return md;
	}
	
	@RequestMapping("/bbs/free/fileDownload.do")
	@ResponseBody
	public byte[] fileDownload(@RequestParam HashMap<String, String> params, HttpServletResponse rep) {
		//1. 泥⑤����쇱�� seq 爰쇰�닿린
		int seq = Integer.parseInt(params.get("attachSeq"));
		//2. seq�� �대�뱁���� 泥⑤����� 1嫄� 媛��몄�ㅺ린
		Attachment attach = as.findById(seq);
		//3. response�� ��蹂� ����
		return fUtil.getDownloadFileBytes(attach, rep);
	}
	
	@RequestMapping("/delAttach.do")
	public ModelAndView delAttach(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		log.debug("paramss :" + params);
		int attachSeq = Integer.parseInt(params.get("attachSeq"));
		try {
			int seq = bs.delAttachedFile(attachSeq);
			md.addObject("num", seq);
			md.addObject("currentPageNo", params.get("currentPageNo"));
			md.addObject("pass", "true");
			RedirectView rv = new RedirectView("/web_portfolio/goUpdate.do");
			md.setView(rv);
		} catch (Exception e) {
			e.printStackTrace();
			RedirectView rv = new RedirectView("/web_portfolio/goUpdate.do");
			md.setView(rv);
		}
		return md;
	}
}

