package com.deappec.appointment.infraestructure.mail;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.SendMailRepository;
import com.deappec.appointment.domain.models.SendEmail;
import com.deappec.appointment.infraestructure.mail.mappers.SendEmailMapper;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SendEmailAdapter implements SendMailRepository {

	private final Resend resend;

	@Override
	public boolean sendMail(SendEmail sendMail) throws ResendException {
		CreateEmailOptions params = CreateEmailOptions.builder().from(sendMail.getFrom()).to(List.of(sendMail.getTo()))
				.subject(sendMail.getSubject()).html(sendMail.getMessageHtml()).build();
		String response = resend.emails().send(params).getId();
		if (response == null) {
			return false;
		}
		return true;
	}

}
