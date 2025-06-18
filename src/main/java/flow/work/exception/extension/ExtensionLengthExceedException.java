package flow.work.exception.extension;

import flow.work.exception.ApplicationException;

import static flow.work.exception.extension.message.ExtensionExceptionMessage.EXTENSION_LENGTH_EXCEED;

public class ExtensionLengthExceedException extends ApplicationException {
    public ExtensionLengthExceedException() {
        super(EXTENSION_LENGTH_EXCEED.getMessage());
    }
}
