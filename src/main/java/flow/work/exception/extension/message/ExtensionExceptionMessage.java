package flow.work.exception.extension.message;

import lombok.Getter;

@Getter
public enum ExtensionExceptionMessage {
    CUSTOM_EXTENSION_ALREADY("이미 등록된 확장자입니다."),
    CUSTOM_EXTENSION_CONFLICT_WITH_FIXED("고정 확장자와 중복될 수 없습니다."),
    CUSTOM_EXTENSION_LIMIT_EXCEED("최대 200개의 확장자만 등록할 수 있습니다."),
    INVALID_EXTENSION_FORMAT("확장자는 알파벳과 숫자만 입력 가능합니다."),
    EXTENSION_LENGTH_EXCEED("최대 20자 까지 가능합니다."),
    ;

    private final String message;

    ExtensionExceptionMessage(String message) {
        this.message = message;
    }
}
