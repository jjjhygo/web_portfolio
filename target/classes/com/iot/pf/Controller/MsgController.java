package com.iot.pf.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.iot.pf.dto.Board;
import com.iot.pf.dto.Friends;
import com.iot.pf.dto.Msg;
import com.iot.pf.dto.User;
import com.iot.pf.service.MsgService;
import com.iot.pf.service.UserService;

@Controller
public class MsgController {
	private Logger log = Logger.getLogger(BoardController.class);
	
	@Autowired
	MsgService ms;
	@Autowired
	UserService us;
	
	@RequestMapping("/msg/goWrite.do")
	public ModelAndView goWrite(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		log.debug("pparams : friend " + params.get("friendUserId"));
		if (session.getAttribute("userId") == null) {  //로그인 하지 않았다면
	         md.setViewName("error/error");
	         md.addObject("msg", "로그인을 먼저 해주세요.");
	         md.addObject("nextLocation", "/gologin.do");
	         return md;
	      }     
		md.addObject("friendUserId", params.get("friendUserId"));
		md.setViewName("msg/writeMsg");
		return md;
	}
	
	@RequestMapping("/msg/write.do")
	public ModelAndView write(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		String userIdSend = params.get("userIdSend");
		String userIdReceive = params.get("userIdReceive");
		String title = params.get("title");
		String contents = params.get("contents");
		
		Msg msg = new Msg();
		msg.setUserIdSend(userIdSend);
		msg.setUserIdReceive(userIdReceive);
		msg.setMsgTitle(title);
		msg.setMsgContents(contents);
		
		try {
			ms.write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		md.setViewName("error/error");
		md.addObject("nextLocation", "/index.do");
		return md;
	}
	
	@RequestMapping("/msg/list.do")
	public ModelAndView list(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		
		if (session.getAttribute("userId") == null) {  //로그인 하지 않았다면
	         md.setViewName("error/error");
	         md.addObject("msg", "로그인을 먼저 해주세요.");
	         md.addObject("nextLocation", "/gologin.do");
	         return md;
	      }
		
		int totalArticle = 0;
		if(params.get("send")!=null) {
			String userIdSend = params.get("send");
			totalArticle = ms.countSend(userIdSend);//보낸 쪽지 총 쪽지 수
		} 
		if(params.get("receive")!=null) {
			String userIdreceive = params.get("receive");
			totalArticle = ms.countReceive(userIdreceive);//받은 쪽지 총 쪽지 수
		} 
		int pageArticle = 10; //페이지당 쪽지수
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
		
		String userId = String.valueOf(session.getAttribute("userId"));
		HashMap<String, Object> P = new HashMap<String, Object>();
		P.put("start", startArticleNo);
		P.put("pageArticle", pageArticle);
		P.put("userId", userId);
		
		if(params.get("send")!=null) {//보낸쪽지함
			md.addObject("send", params.get("send"));
			ArrayList<Msg> result = ms.paging(P);
			md.addObject("result", result);
			md.addObject("currentPageNo", currentPageNo);
			md.addObject("totalPage", totalPage);
			md.addObject("pageBlockSize", pageBlockSize);
			md.addObject("pageBlockEnd", pageBlockEnd);
			md.addObject("pageBlockStart", pageBlockStart);
			md.setViewName("msg/listMsgSend");
		}
		if(params.get("receive")!=null) {//받은쪽지함
			md.addObject("receive", params.get("receive"));
			ArrayList<Msg> result = ms.pagingRe(P);
			md.addObject("result", result);
			md.addObject("currentPageNo", currentPageNo);
			md.addObject("totalPage", totalPage);
			md.addObject("pageBlockSize", pageBlockSize);
			md.addObject("pageBlockEnd", pageBlockEnd);
			md.addObject("pageBlockStart", pageBlockStart);
			md.setViewName("msg/listMsgReceive");
		}
		return md;
	}
	
	@RequestMapping("/msg/readMsg.do")
	public ModelAndView readMsg(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		int seq = Integer.parseInt(params.get("seq"));
		Msg msg = ms.getMsg(seq);
		
		if(params.containsKey("send")) {
			md.addObject("send", params.get("send"));
		}
		if(params.containsKey("receive")) {
			md.addObject("receive", params.get("receive"));
			msg.setMsgCheck("1");
			try {
				ms.chkUpdate(msg);
			} catch (Exception e) {
				e.printStackTrace();
				RedirectView rv = new RedirectView("/msg/readMsg.do?seq="+params.get("seq"));
				md.setView(rv);
				return md;
			}
		}
		md.addObject("currentPageNo", params.get("currentPageNo"));
		md.addObject("msg", msg);
		md.setViewName("msg/readMsg");
		return md;
	}
	
	@RequestMapping("/msg/goReply")
	public ModelAndView goReply(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		int seq = Integer.parseInt(params.get("seq"));
		Msg msg = ms.getMsg(seq);
		
		md.addObject("receive", params.get("receive"));
		md.addObject("currentPageNo", params.get("currentPageNo"));
		md.addObject("seq", seq);
		md.addObject("send", msg.getUserIdSend());
		md.addObject("receive", msg.getUserIdReceive());
		md.setViewName("msg/reply");
		return md;
	}
	
	@RequestMapping("/msg/reply")
	public ModelAndView reply(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		int seq = Integer.parseInt(params.get("seq"));
		int currentPageNo = Integer.parseInt(params.get("currentPageNo"));
		String receive = params.get("receive");
		
		Msg msg = new Msg();
		msg.setUserIdSend(params.get("userIdSend"));
		msg.setUserIdReceive(params.get("userIdReceive"));
		msg.setMsgTitle(params.get("title"));
		msg.setMsgContents(params.get("contents"));
		try {
			ms.write(msg);
		} catch (Exception e) {
			e.printStackTrace();
			RedirectView rv = new RedirectView("/web_portfolio/msg/reply.do");
			md.setView(rv);
			return md;
		}
		RedirectView rv = new RedirectView("/web_portfolio/msg/readMsg.do?seq="+seq+"&currentPageNo="+currentPageNo+"&receive="+receive);
		md.setView(rv);
		return md;
	}
	
	@RequestMapping("/msg/customerList.do")
	public ModelAndView friendsList(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
//		ArrayList<User> user = us.getUserList();
//		md.addObject("user", user);
		int totalArticle = ms.count(); //총 게시글 수
		System.out.println(totalArticle);
		int pageArticle = 10; //페이지 당 게시글 수
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
		log.debug("pparams : " + session.getAttribute("userId"));
		P.put("myUserId", session.getAttribute("userId"));
		P.put("start", startArticleNo);
		P.put("pageArticle", pageArticle);

		ArrayList<Friends> result = ms.fPaging(P);
		
		md.addObject("result", result);
		md.addObject("pageBlockEnd", pageBlockEnd);
		md.addObject("currentPageNo", currentPageNo);
		md.addObject("pageBlockStart", pageBlockStart);
		md.addObject("pageBlockSize", pageBlockSize);
		md.addObject("totalPage", totalPage);
		md.addObject("pageBlockStart", pageBlockStart);
		md.setViewName("msg/customerList");
		return md;
	}
	
	@RequestMapping("/msg/goMakeFriends.do")
	public ModelAndView goMakeFriends(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		
		md.addObject("msg", params.get("msg"));
		md.setViewName("msg/makeFriends");
		return md;
	}
	@RequestMapping("/msg/makeFriends.do")
	public ModelAndView makeFriends(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		String userId = params.get("userId");
		String loginId = String.valueOf(session.getAttribute("userId"));
		User user = us.getUser(userId);
		
		Friends friend= new Friends();
		friend.setMyUserId(loginId);
		friend.setFriendUserId(user.getUserId());
		friend.setFriendUserName(user.getUserName());
		
		try {
			ms.makeFriend(friend);
		} catch (Exception e) {
			e.printStackTrace();
			md.addObject("msg", "<font color=green><b>오류가 발생했습니다.</b></font>");
			RedirectView rv = new RedirectView("/web_portfolio/msg/goMakeFriends.do");
			md.setView(rv);
		}
		RedirectView rv = new RedirectView("/web_portfolio/msg/goMakeFriends.do");
		md.setView(rv);
		return md;
	}
}
