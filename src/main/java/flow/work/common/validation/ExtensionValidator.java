package flow.work.common.validation;

import flow.work.extension.entity.FixedExtensionType;
import flow.work.uploadfile.exception.BlockedExtensionException;
import flow.work.extension.exception.*;
import flow.work.extension.repository.CustomExtensionRepository;
import flow.work.extension.repository.FixedExtensionRepository;
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
