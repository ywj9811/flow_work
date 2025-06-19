package flow.work.extension.service;

import flow.work.common.validation.ExtensionValidator;
import flow.work.extension.dto.req.CustomExtensionAddRequest;
import flow.work.extension.dto.res.CustomExtensionResponse;
import flow.work.extension.entity.CustomExtension;
import flow.work.extension.exception.*;
import flow.work.extension.repository.CustomExtensionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomExtensionServiceTest {
    @Mock
    CustomExtensionRepository customExtensionRepository;
    @Mock
    ExtensionValidator extensionValidator;
    @InjectMocks
    CustomExtensionService customExtensionService;


    @Test
    @DisplayName("모든 커스텀 확장자 조회")
    void allCustomExtensionTest() {
        List<CustomExtension> extensions = List.of(
                new CustomExtension(1L, "EXE"),
                new CustomExtension(2L, "SH")
        );

        given(customExtensionRepository.findAll())
                .willReturn(extensions);

        CustomExtensionResponse response = customExtensionService.allCustomExtension();

        assertThat(response.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("커스텀 확장자 추가")
    void addCustomExtensionTest() {
        CustomExtensionAddRequest request = new CustomExtensionAddRequest(" exe ");

        willDoNothing().given(extensionValidator)
                .validateCustomExtension("EXE");
        customExtensionService.addCustomExtension(request);

        then(customExtensionRepository).should().save(any(CustomExtension.class));
    }

    @Test
    @DisplayName("확장자 길이 초과 예외 발생")
    void addCustomExtensionWithLengthException() {
        String tooLong = "X".repeat(21);
        CustomExtensionAddRequest request = new CustomExtensionAddRequest(tooLong);

        willThrow(new ExtensionLengthExceedException())
                .given(extensionValidator)
                .validateCustomExtension(tooLong.toUpperCase());

        assertThatThrownBy(() -> customExtensionService.addCustomExtension(request))
                .isInstanceOf(ExtensionLengthExceedException.class);
    }

    @Test
    @DisplayName("확장자 불가능 문자 예외 발생")
    void addCustomExtensionWithInvalidExtensionException() {
        String invalid = "abc$#";
        CustomExtensionAddRequest request = new CustomExtensionAddRequest(invalid);

        willThrow(new InvalidExtensionFormatException())
                .given(extensionValidator)
                .validateCustomExtension(invalid.replace(".", "").replaceAll("\\s+", "").toUpperCase());

        assertThatThrownBy(() -> customExtensionService.addCustomExtension(request))
                .isInstanceOf(InvalidExtensionFormatException.class);
    }

    @Test
    @DisplayName("확장자 중복 예외 발생")
    void addCustomExtensionWithLimitExceed() {
        String limitExceed = "EXE";
        CustomExtensionAddRequest request = new CustomExtensionAddRequest(limitExceed);

        willThrow(new CustomExtensionLimitExceedException())
                .given(extensionValidator)
                .validateCustomExtension(limitExceed.toUpperCase());

        assertThatThrownBy(() -> customExtensionService.addCustomExtension(request))
                .isInstanceOf(CustomExtensionLimitExceedException.class);
    }

    @Test
    @DisplayName("확장자 중복 예외 발생")
    void addCustomExtensionWithAlreadyException() {
        String duplicate = "EXE";
        CustomExtensionAddRequest request = new CustomExtensionAddRequest(duplicate);

        willThrow(new CustomExtensionAlreadyException())
                .given(extensionValidator)
                .validateCustomExtension(duplicate.toUpperCase());

        assertThatThrownBy(() -> customExtensionService.addCustomExtension(request))
                .isInstanceOf(CustomExtensionAlreadyException.class);
    }

    @Test
    @DisplayName("고정확장자 중복 예외 발생")
    void addCustomExtensionWithConflictWithFixedException() {
        String fixed = "bat"; // 고정 확장자와 겹치는 예시
        CustomExtensionAddRequest request = new CustomExtensionAddRequest(fixed);

        willThrow(new CustomExtensionConflictWithFixedException())
                .given(extensionValidator)
                .validateCustomExtension(fixed.toUpperCase());

        assertThatThrownBy(() -> customExtensionService.addCustomExtension(request))
                .isInstanceOf(CustomExtensionConflictWithFixedException.class);
    }

    @Test
    @DisplayName("커스텀 확장자 삭제")
    void deleteCustomExtensionTest() {
        long id = 1L;

        willDoNothing().given(customExtensionRepository).deleteById(id);

        customExtensionService.deleteCustomExtension(id);

        then(customExtensionRepository).should().deleteById(id);
    }
}