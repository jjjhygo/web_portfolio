package com.iot.pf.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iot.pf.dto.CntVisit;
import com.iot.pf.service.CntVisitService;

@Controller
public class IndexController {
	@Autowired
	CntVisitService cvs;
	
	@RequestMapping("/index.do")
	public ModelAndView index(@RequestParam HashMap<String, String> params) {
		ModelAndView md = new ModelAndView();
		md.addObject("msg", params.get("msg"));
		int endCount = cvs.todayCount();
		int startCount = endCount -5;
		
		HashMap<String, Object> P = new HashMap<String, Object>();
		P.put("totalCount", endCount);
		P.put("startCount", startCount);
		
		//총방문자와 일일방문자를 담아서 index jsp로 보낸다. 
		ArrayList<CntVisit> result = cvs.visitPaging(P);
		int totalCount = cvs.totalCount();
		int todayCount = cvs.todayCount();
		md.addObject("totalCount", totalCount);
		md.addObject("todayCount", todayCount);
		md.addObject("result", result);
		md.setViewName("index");
		return md;
		
	}
	@RequestMapping("/home.do")
	public ModelAndView home() {
		ModelAndView md = new ModelAndView();
		md.setViewName("home");
		return md;
	}
	
}
