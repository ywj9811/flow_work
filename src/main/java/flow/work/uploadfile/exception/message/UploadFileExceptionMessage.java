package flow.work.uploadfile.exception.message;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum UploadFileExceptionMessage {
    BLOCKED_EXTENSION("차단된 확장자가 포함되어 있습니다.", BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus httpStatus;

    UploadFileExceptionMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
