package flow.work.extension.exception;

import flow.work.common.exception.ApplicationException;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.INVALID_EXTENSION_FORMAT;

public class InvalidExtensionFormatException extends ApplicationException {
    public InvalidExtensionFormatException() {
        super(INVALID_EXTENSION_FORMAT.getMessage(), INVALID_EXTENSION_FORMAT.getHttpStatus());
    }
}
