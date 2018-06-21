package com.iot.pf.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.iot.pf.dto.CntVisit;
import com.iot.pf.dto.User;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.CntVisitService;
import com.iot.pf.service.UserService;


@Controller
public class UserController {
	
	private Logger log = Logger.getLogger(BoardController.class);
	@Autowired
	CntVisitService cvs;
	
	@Autowired
	UserService us;
	
	@RequestMapping("/gologin.do")
	public ModelAndView gologin(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		md.addObject("msg", params.get("msg"));
		md.setViewName("/bbs/free/login");
		return md;
	}
	@RequestMapping("/chkId.do")
	//요청한 곳으로 결과를 다시 돌려보내는 것. 즉 chkId.do로 결과를 보낸다.
	@ResponseBody // 비동기 방식이라는 뜻 //ModelAndView가 없기 때문에 보여줄 jsp자체가 없다.
	public int chkId(@RequestParam HashMap<String, String> params) {
		String userId = params.get("userId");
		return us.chkDuplication(userId);
	}
	@RequestMapping("/user/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		String userId = params.get("j_userId");
		String userPw = params.get("j_userPw");
		String nickname = params.get("j_nickname");
		String userName = params.get("j_userName");
		String email = params.get("j_email");
		
		HashMap<String, Object> R = new HashMap<String, Object>();
		R.put("userId", userId);
		R.put("userPw", userPw);
		R.put("nickname", nickname);
		R.put("userName", userName);
		R.put("email", email);
		
		us.register(R);
		
		RedirectView rv = new RedirectView("/web_portfolio/gologin.do");
		md.setView(rv);
		return md;
	}
	@RequestMapping("/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		
		log.debug("/login.do - params : " + params);
		CntVisit visit = new CntVisit();
		String pw = params.get("userPw");
		String id = params.get("userId");
		String isAdmin=params.get("isAdmin");
		
		try {
			if(us.comparePw(pw, id)) {
				User user = us.getUser(id);
				visit.setUserId(id);
				cvs.insert(visit);
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("isAdmin", user.getIsAdmin());
				
			RedirectView rv = new RedirectView("/web_portfolio/index.do");
			md.setView(rv);
			}
			else {
				System.out.println("불일치");
				RedirectView rv = new RedirectView("/web_portfolio/gologin.do");
				md.addObject("msg", "<font color=red><b>비밀번호 불일치</b></font>");
				md.setView(rv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			switch (e.getMessage()) {
			case "NOT_FOUND_USERID" :
				md.setViewName("/bbs/free/login");
				md.addObject("msg", "<font color='green'><b>ID가 없습니다.</b></font>");
				break;
			}
		}
		
		return md;
	}
	@RequestMapping("/logout.do")
	public ModelAndView userList(HttpSession session) {
		ModelAndView md = new ModelAndView();
		//로그아웃 session은 공유를 안한다.
		session.invalidate();
		RedirectView rv = new RedirectView("/web_portfolio/gologin.do");
		md.setView(rv);
		return md;
	}
	@RequestMapping("/user/list.do")
	public ModelAndView list(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		if(session.getAttribute("userId")!=null) {
			//관리자
			if(session.getAttribute("isAdmin").equals("1")) {
				md.setViewName("user/list");
				return md;
			}
			else {
				md.setViewName("error/error");
				md.addObject("msg", "권한이 없습니다.");
				md.addObject("nextLocation", "/index.do");
				return md;
			}
		}
		else {
			md.setViewName("error/error");
			md.addObject("msg", "로그인 하세요.");
			md.addObject("nextLocation", "/gologin.do");
			return md;
		}
	}
	@RequestMapping("/getUserlistData.do")
	@ResponseBody
	public HashMap<String, Object> list2(@RequestParam HashMap<String, String> params) {
		//기본적으로 파라미터 6개가 들어온다.
		log.debug("/getUserlistData.do pparams : "+ params);
		//parameter for paging
		int pageSize = Integer.parseInt(params.get("rows").toString()); //한 페이지에 보여줄 게시글 수
		int currentPage = Integer.parseInt(params.get("page").toString()); // 현재 페이지
		int totalCount = us.count(); //총 게시글 수
		int totalPage = (totalCount % pageSize == 0)? totalCount/pageSize:totalCount/pageSize+1;
		int start= (currentPage - 1) * pageSize;// 시작 로우

		//jsp에서 넘어오는걸 params로 받고 있고 그 params에 바로 넣어서 dao로 보낸다.
		params.put("start", String.valueOf(start));
		
		ArrayList<User> result = us.jpaging(params);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", currentPage); //현재 페이지
		resultMap.put("total", totalPage); //총 페이지 수
		resultMap.put("records", totalCount); // 총 데이터 건수
		resultMap.put("rows", result); //보여줄 데이터 15건
		
		return resultMap;
		
	}
	
	@RequestMapping("/user/checkPk.do")
	@ResponseBody
	public long checkPk(@RequestParam HashMap<String, String> params) {
		log.debug("/user/chekPk.do seq : "+params.get("seq"));
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}
	
	@RequestMapping("/user/delUser.do")
	@ResponseBody
	public int delUser(@RequestParam HashMap<String, String> params){
		log.debug("paramss : "+params);
		int seq = Integer.parseInt(params.get("id"));
		int result = 0;
		try {
			result = us.delete(seq);
			return result;
		}catch(AnomalyException ae) {
			ae.printStackTrace();
			return 5;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 7;
		}
	}
	
	@RequestMapping("/user/editUser.do")
	@ResponseBody
	public int editUser(@RequestParam HashMap<String, String> params) {
		log.debug("pparams : "+params);
		int seq = Integer.parseInt(params.get("id")); 
		User user = us.findUserBySeq(seq); //수정할 게시물 1건 꺼내오기
		user.setUserName(params.get("userName"));
		user.setNickname(params.get("nickname"));
		user.setIsAdmin(params.get("isAdmin"));
		int result = 0;
		try {
			result = us.update(user);
			return result;
		}catch(AnomalyException ae) {
			ae.printStackTrace();
			return 5;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 6;
		}
		
	}
	@RequestMapping("/profile.do")
	public ModelAndView profile(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		String userId = params.get("userId");
		
		if(session.getAttribute("userId")==null) {
			md.setViewName("error/error");
			md.addObject("msg", "로그인 먼저 해주세요.");
			md.addObject("nextLocation", "/index.do");
			
			return md;
		}
		User user =us.getUser(userId);
		md.addObject("user", user);
		md.addObject("msg", params.get("msg"));
		md.setViewName("profile");
		return md;
	}
	
	@RequestMapping("/userDelete.do")
	public ModelAndView userDelete(@RequestParam HashMap<String, String> params, HttpSession session) {
		ModelAndView md = new ModelAndView();
		int seq = Integer.parseInt(params.get("seq"));
		String id = params.get("userId");
		String pw = params.get("password");
		try {
			if(us.comparePw(pw, id)) {
				us.delete(seq);
				md.setViewName("error/error");
				md.addObject("msg", "탈퇴가 완료되었습니다.");
				md.addObject("nextLocation", "/login.do");
				
			}
			else {
				RedirectView rv = new RedirectView("/web_portfolio/profile.do");
				md.addObject("msg", "<font color =green><b>비밀번호 불일치</b></font>");
				md.addObject("userId", id);
				md.setView(rv);
				return md;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RedirectView rv = new RedirectView("/web_portfolio/profile.do");
			md.addObject("userId", id);
			md.setView(rv);
			return md;
		}
		
		return md;
	}
	
	@RequestMapping("/goProfileUpdate.do")
	public ModelAndView goProfileUpdate(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		System.out.println("--------------"+ params);
		String userId = params.get("userId");
		User user = us.getUser(userId);
		md.addObject("user", user);
		md.addObject("msg", params.get("msg"));
		md.setViewName("pUpdate");
		return md;
	}
	
	@RequestMapping("/profileUpdate.do")
	public ModelAndView ProfileUpdate(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		String pw = params.get("password");
		String id = params.get("userId");
		User user = us.getUser(id);
		user.setNickname(params.get("nickname"));
		user.setEmail(params.get("email"));
		try {
			if(us.comparePw(pw, id)) {
				us.update(user);
				md.addObject("userId", id);
				RedirectView rv = new RedirectView("/web_portfolio/profile.do");
				md.setView(rv);
				
			}
			else {
				RedirectView rv = new RedirectView("/web_portfolio/goProfileUpdate.do");
				md.addObject("userId", id);
				md.addObject("msg", "<font color=green><b>비밀번호 불일치</b></font>");
				md.setView(rv);
				return md;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			RedirectView rv = new RedirectView("/web_portfolio/goProfileUpdate.do");
			md.setView(rv);
			return md;
		}
		return md;
	}
}
