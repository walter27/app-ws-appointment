package com.deappec.appointment.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmail {

	private String to;
	private String subject;
	private String messageHtml;
	private String from;

}
