package flow.work.common;

import flow.work.common.validation.ExtensionValidator;
import flow.work.extension.entity.FixedExtension;
import flow.work.extension.entity.FixedExtensionType;
import flow.work.extension.exception.*;
import flow.work.extension.repository.CustomExtensionRepository;
import flow.work.extension.repository.FixedExtensionRepository;
import flow.work.uploadfile.exception.BlockedExtensionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ExtensionValidatorTest {
    @Mock
    CustomExtensionRepository customExtensionRepository;

    @Mock
    FixedExtensionRepository fixedExtensionRepository;

    @InjectMocks
    ExtensionValidator extensionValidator;

    @Test
    @DisplayName("업로드 파일 커스텀 확장자 차단 예외 발생")
    void validateCustomExtensionBlockException() {
        given(customExtensionRepository.existsByName("EXE")).willReturn(true);

        assertThatThrownBy(() -> extensionValidator.validate("EXE"))
                .isInstanceOf(BlockedExtensionException.class);
    }

    @Test
    @DisplayName("업로드 파일 고정 확장자 차단 예외 발생")
    void validateFixedExtensionBlock() {
        for (FixedExtensionType type : FixedExtensionType.values()) {
            given(fixedExtensionRepository.findByNameAndIsCheckTrue(type))
                    .willReturn(Optional.of(new FixedExtension()));

            assertThatThrownBy(() -> extensionValidator.validate(String.valueOf(type)))
                    .isInstanceOf(BlockedExtensionException.class);
        }
    }

    @Test
    @DisplayName("확장자 길이 초과 시 예외 발생")
    void validateCustomExtensionLengthExceed() {
        String extension = "A".repeat(21);

        assertThatThrownBy(() -> extensionValidator.validateCustomExtension(extension))
                .isInstanceOf(ExtensionLengthExceedException.class);
    }

    @Test
    @DisplayName("확장자에 허용되지 않은 특수문자 포함시 예외 발생")
    void validateCustomExtensionInvalidCharacter() {
        assertThatThrownBy(() -> extensionValidator.validateCustomExtension("JS#"))
                .isInstanceOf(InvalidExtensionFormatException.class);
    }

    @Test
    @DisplayName("커스텀 확장자 200개 이상이면 예외 발생")
    void validateCustomExtensionLimitExceed() {
        given(customExtensionRepository.count()).willReturn(200L);

        assertThatThrownBy(() -> extensionValidator.validateCustomExtension("EXE"))
                .isInstanceOf(CustomExtensionLimitExceedException.class);
    }

    @Test
    @DisplayName("커스텀 확장자 중복 시 예외 발생")
    void validateCustomExtensionDuplicate() {
        given(customExtensionRepository.existsByName("EXE")).willReturn(true);

        assertThatThrownBy(() -> extensionValidator.validateCustomExtension("EXE"))
                .isInstanceOf(CustomExtensionAlreadyException.class);
    }

    @Test
    @DisplayName("고정 확장자 목록과 충돌 시 예외 발생")
    void validateCustomExtension_conflictWithFixed() {
        for (FixedExtensionType type : FixedExtensionType.values()) {
            String fixedExtension = type.name();

            assertThatThrownBy(() -> extensionValidator.validateCustomExtension(fixedExtension))
                    .isInstanceOf(CustomExtensionConflictWithFixedException.class);
        }
    }

}
