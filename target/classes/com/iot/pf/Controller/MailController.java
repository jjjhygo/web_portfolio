package com.iot.pf.Controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iot.pf.util.MailUtil;

@Controller
public class MailController {
	@Autowired
	MailUtil mUtil;
	private Logger log = Logger.getLogger(MailController.class);
	
	@RequestMapping("/goSendMessage.do")
	public ModelAndView goSendMessage(@RequestParam Map<String, String> params, HttpSession session) {
		ModelAndView md= new ModelAndView();
		
		if (session.getAttribute("userId") == null) { //로그인하지 않았다면
	         md.setViewName("error/error");
	         md.addObject("msg", "");
	         md.addObject("nextLocation", "/gologin.do");
	         return md;
	      }
		md.setViewName("mail/mail");
		return md;
	}
	
	@RequestMapping("/sendMessage.do")
	public ModelAndView sendMessage(@RequestParam Map<String, String> params){
		ModelAndView md = new ModelAndView();
		try {
			mUtil.sendEmail(params);
		} catch (Exception e) {

			e.printStackTrace();
			md.setViewName("error/error");
	        md.addObject("msg", "메일을 보내는 도중 오류가 발생하였습니다.");
	        md.addObject("nextLocation", "/index.do");
		}
		md.setViewName("error/error");
        md.addObject("msg", "메일이 성공적으로 전송되었습니다. Thank you :)");
        md.addObject("nextLocation", "/index.do");
		
		return md;
	}
}
