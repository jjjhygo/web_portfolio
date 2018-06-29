package com.iot.pf.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.iot.pf.dto.Comment;
import com.iot.pf.dto.User;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.CommentService;
import com.iot.pf.service.UserService;

@Controller
public class CommentController {
	@Autowired
	UserService us;
	@Autowired
	CommentService cs;
	
	@RequestMapping("/cmtInsert.do")
	public ModelAndView cmtInsert(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		System.out.println("--------------"+params);
		User user = us.getUser(String.valueOf(session.getAttribute("userId")));
		int boardSeq = Integer.parseInt(params.get("num"));
		int userSeq = Integer.valueOf(user.getSeq());
		String contents = params.get("contents");
		
		Comment comment = new Comment(); 
		comment.setNickname(user.getNickname());
		comment.setUserSeq(userSeq);
		comment.setBoardSeq(boardSeq);
		comment.setContents(contents);
		
		try {
			cs.insert(comment);
			md.addObject("num", boardSeq);
			RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do?currentPageNo="+params.get("currentPageNo"));
			md.setView(rv);
		} catch (AnomalyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do?currentPageNo="+params.get("currentPageNo"));
			md.setView(rv);
			return md;
		}
		
		return md;
		
	}
	@RequestMapping("/cmtDelete.do")
	public ModelAndView cmtDelete(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		String id = String.valueOf(session.getAttribute("userId"));
		String userId = params.get("userId");
		int seq = Integer.parseInt(params.get("seq"));
		int num = Integer.parseInt(params.get("num"));
		if(id.equals(userId)) {
			try {
				cs.delete(seq);
				md.addObject("num", num);
				RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do");
				md.setView(rv);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				md.addObject("num", num);
				RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do");
				md.setView(rv);
				return md;
			}
		}
		else {
			md.addObject("msg", "<font color=green><b>다른 사용자의 답글을 지울 수 없습니다.</b></font>");
			md.addObject("num", num);
			RedirectView rv = new RedirectView("/web_portfolio/bbs/free/readArticle.do");
			md.setView(rv);
			return md;
			
		}
		return md;
	}
}
