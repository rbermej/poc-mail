package demo.mail.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailContentBuilder {

	private final TemplateEngine templateEngine;

	public String build(final String template, final Map<String, Object> variables) {
		final Context context = new Context();
		context.setVariables(variables);
		return templateEngine.process(template, context);
	}

}