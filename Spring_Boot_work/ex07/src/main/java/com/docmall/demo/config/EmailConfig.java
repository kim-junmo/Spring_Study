package com.docmall.demo.config;

import java.security.GeneralSecurityException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그를 사용하기 위한 목적때문에 사용한다.
@Configuration //bean등록을 작업하기 위해 사용한다.
@PropertySource("classpath:mail/email.properties") //email.properties를 사용하기 위해 작성.
public class EmailConfig {

	public EmailConfig() throws Exception {
		log.info("emailconfig.java constructor call");
	}
	
	//email.properties 파일의 설정 정보를 참조
	@Value("${spring.mail.transport.protocol}")
	private String protocol; //smtp값이 들어오게 됨.
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean auth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.required}")
	private boolean starttls;
		
	@Value("${spring.mail.debug}")
	private boolean debug;
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.default-encoding}")
	private String encoding;

	@Bean //Bean : 스프링에서 주입을 목적으로 하는 객체를 bean이라고 생각하면 된다.
	//JavaMailSender : 스프링에서 메일 발송하는 객체.
	public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties properties = new Properties(); //https://dev-cini.tistory.com/82
		
		//추가 MAP관련 컬렉션은 put으로 데이터를 추가, list, set은 add를 사용
		properties.put("mail.transport.protocol",protocol);
		properties.put("mail.smtp.auth",auth);
		properties.put("mail.smtp.starttls",starttls );
		properties.put("mail.smtp.debug",debug );
		properties.put("mail.smtp.ssl.protocol","TLSV1.2");
		properties.put("mail.smtp.socketFactory.port","25");
		properties.put("javax.net.ssl.SSLSocketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback","ture");
		
		
		
		/********************************************/
		/* import패키지 나중 확인할 것. */
		MailSSLSocketFactory sf = null;	
		
		try {
			sf = new MailSSLSocketFactory();
		}catch(GeneralSecurityException e) {
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.socketFactory", sf);
		//추가 끝
		
		/********************************************/
		
		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.setPort(port);
		mailSender.setJavaMailProperties(properties);
		mailSender.setDefaultEncoding(encoding);
		
		
		return mailSender;
	}
}
