package com.cit.usermanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
	
    @Value("${app.base.url}") // Set this in your application properties
    private String baseUrl;
	
	@Value("${spring.mail.host}") // Set this in your application properties
    private String host;
	@Value("${spring.mail.port}") // Set this in your application properties
    private Integer port;
	@Value("${spring.mail.username}") // Set this in your application properties
    private String username;
	@Value("${spring.mail.password}") // Set this in your application properties
    private String password;
	
	@Value("${spring.mail.properties.mail.smtp.auth}") // Set this in your application properties
    private String auth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}") // Set this in your application properties
    private String starttls;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        //props.put("mail.smtp.ssl.enable", "true");
        return mailSender;
    }
    
    public void sendVerificationEmail(String to, String token) {
        String subject = "Email Verification details for continuous incidents telemetry account";
        String confirmationLink = baseUrl + "/verify?token=" + token;

        String text = "Click the link below to verify your email:\n" + confirmationLink;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender().send(message);
    }
}
