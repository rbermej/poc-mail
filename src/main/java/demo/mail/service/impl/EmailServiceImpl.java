package demo.mail.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import demo.mail.controller.rest.dto.Email;
import demo.mail.service.EmailService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	@Value("${spring.mail.username:none@mail.com}")
	private String sender;

	private final JavaMailSender emailSender;

	private final SimpleMailMessage template;

	private final MailContentBuilder mailContentBuilder;

	@Override
	public void sendSimpleMessage(final Email email) throws MessagingException {
		final SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(sender);
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());

		emailSender.send(message);
	}

	@Override
	public void sendMessageWithAttachment(final Email email, final MultipartFile file) throws MessagingException {

		final MimeMessage message = emailSender.createMimeMessage();

		final MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(sender);
		helper.setTo(email.getTo());
		helper.setSubject(email.getSubject());
		helper.setText(email.getText());

		helper.addAttachment(file.getOriginalFilename(), file::getInputStream, file.getContentType());

		emailSender.send(message);

	}

	@Override
	public void sendSimpleMessageWithTemplate(final Email email) throws MessagingException {
		// fromBean(email);
		fromThymeleaf(email);
	}

	private void fromBean(final Email email) {
		final String text = String.format(template.getText(), "some@mail.com");

		final SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(sender);
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(text);

		emailSender.send(message);
	}

	private void fromThymeleaf(final Email email) throws MessagingException {
		final MimeMessage message = emailSender.createMimeMessage();

		final MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(sender);
		helper.setTo(email.getTo());
		helper.setSubject(email.getSubject());

		final Map<String, Object> variables = new HashMap<>();
		variables.put("to", email.getTo());
		variables.put("from", sender);
		final String content = mailContentBuilder.build("mail-template.html", variables);
		helper.setText(content, true);

		emailSender.send(message);
	}

}
