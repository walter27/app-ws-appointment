package com.deappec.appointment.infraestructure.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "app")
public class AppProperties {

	@Valid
	private UrlProperties url = new UrlProperties();

	@Valid
	private MailProperties mail = new MailProperties();

	@Valid
	private FilesProperties files = new FilesProperties();

	@Getter
	@Setter
	public static class UrlProperties {
		@NotBlank
		private String frontend;
	}

	@Getter
	@Setter
	public static class MailProperties {
		@NotBlank
		private String from;
		private String resendApiKey;
	}

	@Getter
	@Setter
	public static class FilesProperties {
		@Valid
		private ShiftAssignedFileProperties shiftAssigned = new ShiftAssignedFileProperties();
	}

	@Getter
	@Setter
	public static class ShiftAssignedFileProperties {
		@NotBlank
		private String path;

		private List<String> allowedExtensions = new ArrayList<>();

		@Min(1)
		private long maxSizeBytes = 3L * 1024 * 1024;
	}
}
