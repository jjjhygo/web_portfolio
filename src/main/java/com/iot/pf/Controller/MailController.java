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
		
		if (session.getAttribute("userId") == null) {    // 濡�洹몄�� ��吏� ���� 寃쎌��   
	         md.setViewName("error/error");
	         md.addObject("msg", "濡�洹몄�� �� �댁�⑺�댁＜�몄��.");
	         md.addObject("nextLocation", "/gologin.do");
	         return md;
	      }
		
		md.setViewName("mail/mail");
		return md;
	}
	
	@RequestMapping("/sendMessage.do")
	public ModelAndView sendMessage(@RequestParam Map<String, String> params){
		ModelAndView md = new ModelAndView();
		System.out.println("================"+params);
		
		try {
			mUtil.sendEmail(params);

		} catch (Exception e) {

			e.printStackTrace();
			md.setViewName("error/error");
	        md.addObject("msg", "硫��쇱�� 蹂대�몃�� ��以� �ㅻ�媛� 諛��������듬����.");
	        md.addObject("nextLocation", "/index.do");
		}
		md.setViewName("error/error");
        md.addObject("msg", "�깃났���쇰� 硫��쇱�� 蹂대���듬���� Thank you :)");
        md.addObject("nextLocation", "/index.do");
		
		return md;
	}
}
