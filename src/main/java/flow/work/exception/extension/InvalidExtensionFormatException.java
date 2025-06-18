package flow.work.exception.extension;

import flow.work.exception.ApplicationException;

import static flow.work.exception.extension.message.ExtensionExceptionMessage.INVALID_EXTENSION_FORMAT;

public class InvalidExtensionFormatException extends ApplicationException {
    public InvalidExtensionFormatException() {
        super(INVALID_EXTENSION_FORMAT.getMessage());
    }
}
