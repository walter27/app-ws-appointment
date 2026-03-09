package com.deappec.appointment.infraestructure.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.deappec.appointment.domain.exceptions.FileStorageException;
import com.deappec.appointment.domain.exceptions.InvalidAttachmentException;
import com.deappec.appointment.infraestructure.configuration.AppProperties;

@Component
public class ShiftAssignedAttachmentStorage {

	private final Path rootPath;
	private final AppProperties.ShiftAssignedFileProperties properties;

	public ShiftAssignedAttachmentStorage(AppProperties appProperties) {
		this.properties = appProperties.getFiles().getShiftAssigned();
		this.rootPath = Path.of(properties.getPath()).toAbsolutePath().normalize();
	}

	public String store(MultipartFile file) {
		if (Objects.isNull(file) || file.isEmpty()) {
			throw new InvalidAttachmentException("Attached file is required");
		}

		validateSize(file);

		String originalName = StringUtils.cleanPath(Objects.requireNonNullElse(file.getOriginalFilename(), "file"));
		validateFileName(originalName);
		validateExtension(originalName);
		String safeName = originalName.replace("..", "").replace("/", "").replace("\\", "");
		String fileName = UUID.randomUUID() + "_" + safeName;
		Path target = rootPath.resolve(fileName).normalize();
		if (!target.startsWith(rootPath)) {
			throw new FileStorageException("Invalid file path");
		}
		try {
			Files.createDirectories(rootPath);
			if (!Files.isWritable(rootPath)) {
				throw new FileStorageException("Storage path is not writable: " + rootPath);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
			}
			return target.toString();
		} catch (IOException exception) {
			throw new FileStorageException("Could not store attached file", exception);
		}
	}

	private void validateSize(MultipartFile file) {
		if (file.getSize() > properties.getMaxSizeBytes()) {
			throw new InvalidAttachmentException("Attached file exceeds the maximum allowed size");
		}
	}

	private void validateFileName(String originalName) {
		if (!StringUtils.hasText(originalName)) {
			throw new InvalidAttachmentException("Attached file name is required");
		}
		if (originalName.length() > 150) {
			throw new InvalidAttachmentException("Attached file name is too long");
		}
	}

	private void validateExtension(String originalName) {
		List<String> configured = properties.getAllowedExtensions();
		Set<String> allowedExtensions = configured == null ? Set.of()
				: configured.stream().filter(StringUtils::hasText).map(this::normalizeExtension).collect(Collectors.toSet());

		if (allowedExtensions.isEmpty()) {
			throw new InvalidAttachmentException("No allowed extensions configured for attached files");
		}

		int lastDot = originalName.lastIndexOf('.');
		if (lastDot < 0 || lastDot == originalName.length() - 1) {
			throw new InvalidAttachmentException("Attached file must include a valid extension");
		}

		String extension = normalizeExtension(originalName.substring(lastDot + 1));
		if (!allowedExtensions.contains(extension)) {
			throw new InvalidAttachmentException("Attached file extension is not allowed");
		}
	}

	private String normalizeExtension(String extension) {
		String normalized = extension.trim().toLowerCase(Locale.ROOT);
		if (normalized.startsWith(".")) {
			return normalized.substring(1);
		}
		return normalized;
	}
}
