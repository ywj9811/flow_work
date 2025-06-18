package flow.work.uploadfile.exception.message;

import lombok.Getter;

@Getter
public enum UploadFileExceptionMessage {
    BLOCKED_EXTENSION("차단된 확장자가 포함되어 있습니다"),
    ;

    private final String message;

    UploadFileExceptionMessage(String message) {
        this.message = message;
    }
}
