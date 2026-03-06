package com.deappec.appointment.application.repositories;

import com.deappec.appointment.domain.models.SendEmail;

public interface SendEmailRepository {
	
	boolean sendMail(SendEmail sendMail);
	
}
