package demo.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class MailConfig {

	/**
	 * Bean utilizado para configurar el servicio de MailSender (no hace falta si se
	 * pone en properties) IMPORTANTE: Si declaras un bean, sobreesribirás las
	 * propiedades de resources
	 */
	// @Bean
	// public JavaMailSender getJavaMailSender() {
	// final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	// mailSender.setHost("smtp.gmail.com");
	// mailSender.setPort(587);
	// mailSender.setUsername("mail");
	// mailSender.setPassword("***");
	// mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
	// mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable",
	// "true");
	// return mailSender;
	// }

	/**
	 * Bean utilizado para crear plantillas
	 */
	@Bean
	public SimpleMailMessage templateSimpleMessage() {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setText("This is the test email template for your email:\n%s\n");
		return message;
	}

}
