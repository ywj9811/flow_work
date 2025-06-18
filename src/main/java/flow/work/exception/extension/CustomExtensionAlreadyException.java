package flow.work.exception.extension;

import flow.work.exception.ApplicationException;

import static flow.work.exception.extension.message.ExtensionExceptionMessage.CUSTOM_EXTENSION_ALREADY;

public class CustomExtensionAlreadyException extends ApplicationException {
    public CustomExtensionAlreadyException() {
        super(CUSTOM_EXTENSION_ALREADY.getMessage());
    }
}
