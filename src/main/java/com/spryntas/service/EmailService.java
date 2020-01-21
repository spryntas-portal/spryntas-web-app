package com.spryntas.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	private static final Logger LOGGER = LogManager.getLogger(EmailService.class);

	@Value("${email.from}")
	String emailFrom;

	@Autowired
	public JavaMailSender javaMailSender;

	public void sendSimpleMessage(String to, String subject, String plainTextBody) {
		LOGGER.info("Sending simple Message Mail from JavaMailSender to :" + to);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(plainTextBody);
		javaMailSender.send(message);

	}

	public void sendMessageWithAttachment(String to, String subject, String htmlMsg, String plainTextMsg,
			String pathToAttachment) throws MessagingException {

		LOGGER.info("Sending simple Message with attachment Mail from JavaMailSender to :" + to);
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(emailFrom);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(plainTextMsg, htmlMsg);
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("attachment", file);
		javaMailSender.send(message);
	}

	public void sendHtmlMail(String to, String subject, String htmlMsg, String plainTextMsg) {
		sendHtmlMail(to, null, subject, htmlMsg, plainTextMsg);
	}

	public void sendHtmlMail(String to, String cc, String subject, String htmlMsg, String plainTextMsg) {
		try {
			LOGGER.info("Sending simple html Mail from JavaMailSender to :" + to);
			MimeMessage message = javaMailSender.createMimeMessage();
			message.setSubject(subject);
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(emailFrom);
			helper.setTo(to);
			if (cc != null) {
				helper.setCc(cc);
			}
			helper.setText(plainTextMsg, htmlMsg);
			javaMailSender.send(message);
		} catch (MessagingException ex) {
			LOGGER.error(ex);
		}
	}

}
