package com.iot.pf.util;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
   
   private Logger log = Logger.getLogger(MailUtil.class); 
   
   @Autowired
    protected JavaMailSender  mailSender;
   
   public void sendEmail(Map<String, String> params) throws Exception {
	   System.out.println("=======" + params);
      MimeMessage msg = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(msg, true);

      msg.setFrom(params.get("email"));
      msg.setRecipient(RecipientType.TO, new InternetAddress("jjjhygo91@gmail.com"));
      msg.setSubject("관리자에게 쓰는 메일 from web_portfolio"+"<"+params.get("searchType")+">");
      msg.setText(" ▶ Message ◀ \n\t- "+params.get("message")+"\n\n ▶ From ◀ \n\t- "+params.get("name") +"\n\n ▶ Email ◀ \n\t- "+params.get("email"));

      mailSender.send(msg);
   }
}
