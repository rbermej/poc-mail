package demo.mail.controller.rest.impl;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import demo.mail.controller.rest.EmailController;
import demo.mail.controller.rest.dto.Email;
import demo.mail.service.EmailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mail-poc/v1")
@RequiredArgsConstructor
public class EmailControllerImpl implements EmailController {

	private final EmailService emailServiceImpl;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/simple")
	public void sendSimpleMessage(@Valid @RequestBody final Email email) throws MessagingException {
		emailServiceImpl.sendSimpleMessage(email);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/attachment")
	public void sendMessageWithAttachment(@Valid @RequestBody final Email email, @RequestParam final MultipartFile file)
			throws MessagingException {
		emailServiceImpl.sendMessageWithAttachment(email, file);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/template")
	public void sendSimpleMessageWithTempalte(@Valid @RequestBody final Email email) throws MessagingException {
		emailServiceImpl.sendSimpleMessageWithTemplate(email);
	}

}
