package demo.mail.controller.rest.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Email {

	@NotNull
	private String to;

	@NotNull
	private String subject;

	private String text;

}
