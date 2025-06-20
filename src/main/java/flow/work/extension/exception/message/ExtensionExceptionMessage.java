package flow.work.extension.exception.message;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ExtensionExceptionMessage {
    CUSTOM_EXTENSION_ALREADY("이미 등록된 확장자입니다.", CONFLICT),
    CUSTOM_EXTENSION_CONFLICT_WITH_FIXED("고정 확장자와 중복될 수 없습니다.", CONFLICT),
    CUSTOM_EXTENSION_LIMIT_EXCEED("최대 200개의 확장자만 등록할 수 있습니다.", BAD_REQUEST),
    INVALID_EXTENSION_FORMAT("확장자는 알파벳과 숫자만 입력 가능합니다.", BAD_REQUEST),
    EXTENSION_LENGTH_EXCEED("최대 20자 까지 가능합니다.", BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus httpStatus;

    ExtensionExceptionMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
