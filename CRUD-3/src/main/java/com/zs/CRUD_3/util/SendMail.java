package com.zs.CRUD_3.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@Component
@ActiveProfiles("163")
public class SendMail {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public void sendSimpleMail(String mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
        message.setTo(mail);
        message.setSubject("邮箱认证");
        message.setText("账号创建完毕");
        mailSender.send(message);
        
	}
}
