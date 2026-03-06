package com.deappec.appointment.infraestructure.mail;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.SendEmailRepository;
import com.deappec.appointment.domain.models.SendEmail;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Component
public class SendEmailAdapter implements SendEmailRepository {

	private final Resend resend;

	@Override
	@SneakyThrows(ResendException.class)
	public boolean sendMail(SendEmail sendMail) {
		CreateEmailOptions params = CreateEmailOptions.builder().from(sendMail.getFrom()).to(List.of(sendMail.getTo()))
				.subject(sendMail.getSubject()).html(sendMail.getMessageHtml()).build();
		String responseId = resend.emails().send(params).getId();
		return Objects.nonNull(responseId);
	}

}
