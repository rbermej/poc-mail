package demo.mail.service;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import demo.mail.controller.rest.dto.Email;

public interface EmailService {

	void sendSimpleMessage(Email email) throws MessagingException;

	void sendMessageWithAttachment(Email email, MultipartFile file) throws MessagingException;

	void sendSimpleMessageWithTemplate(Email email) throws MessagingException;

}
