package flow.work.validation;

import flow.work.entity.extension.FixedExtensionType;
import flow.work.exception.extension.*;
import flow.work.exception.uploadfile.BlockedExtensionException;
import flow.work.repository.extension.CustomExtensionRepository;
import flow.work.repository.extension.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ExtensionValidator {

    private final CustomExtensionRepository customExtensionRepository;
    private final FixedExtensionRepository fixedExtensionRepository;

    public void validate(String extension) {
        if (customExtensionRepository.existsByName(extension)) {
            throw new BlockedExtensionException();
        }

        FixedExtensionType.from(extension).ifPresent(value -> {
            if (fixedExtensionRepository.findByNameAndIsCheckTrue(value).isPresent()) {
                throw new BlockedExtensionException();
            }
        });
    }

    public void validateCustomExtension(String cleaned) {
        if (cleaned.length() > 20) {
            throw new ExtensionLengthExceedException();
        }

        if (!cleaned.matches("^[A-Z0-9]+$")) {
            throw new InvalidExtensionFormatException();
        }

        if (customExtensionRepository.count() >= 200) {
            throw new CustomExtensionLimitExceedException();
        }

        if (customExtensionRepository.existsByName(cleaned)) {
            throw new CustomExtensionAlreadyException();
        }

            if (Arrays.stream(FixedExtensionType.values())
                .anyMatch(type -> type.name().equalsIgnoreCase(cleaned))) {
            throw new CustomExtensionConflictWithFixedException();
        }
    }
}
