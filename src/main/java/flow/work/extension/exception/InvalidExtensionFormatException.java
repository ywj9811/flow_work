package flow.work.extension.exception;

import flow.work.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.INVALID_EXTENSION_FORMAT;

public class InvalidExtensionFormatException extends ApplicationException {
    public InvalidExtensionFormatException() {
        super(INVALID_EXTENSION_FORMAT.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
