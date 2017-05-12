package com.goveca.listener;

import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.goveca.Entity.AccountEntity;
import com.goveca.event.OnRegistrationCompleteEvent;
import com.goveca.service.IAccountService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		AccountEntity account = event.getAccount();
		String token = UUID.randomUUID().toString();
		accountService.createVerificationToken(account, token);

		String recipientAddress = account.getEmail();
		String subject = "Registration Confirmation";
		String confirmationUrl = "http://localhost:8080" + "/registrationConfirm?token=" + token;
		Locale locale = LocaleContextHolder.getLocale();
		//String message = messages.getMessage("Please confirm your email address.", null, locale);

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setFrom("abhishekpatyalnith@gmail.com");
		email.setSubject(subject);
		email.setText("Hi Goveca, please click on the link to verify your account."+confirmationUrl);
		mailSender.send(email);
	}
}
