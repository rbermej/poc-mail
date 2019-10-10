package demo.mail.controller.rest;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import demo.mail.controller.rest.dto.Email;

public interface EmailController {

	void sendSimpleMessage(Email email) throws MessagingException;

	void sendMessageWithAttachment(Email email, MultipartFile file) throws MessagingException;

	void sendSimpleMessageWithTempalte(Email email) throws MessagingException;

}
