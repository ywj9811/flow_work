package flow.work.extension.exception;

import flow.work.common.exception.ApplicationException;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.CUSTOM_EXTENSION_ALREADY;

public class CustomExtensionAlreadyException extends ApplicationException {
    public CustomExtensionAlreadyException() {
        super(CUSTOM_EXTENSION_ALREADY.getMessage());
    }
}
