package flow.work.exception.extension;

import flow.work.exception.ApplicationException;

import static flow.work.exception.extension.message.ExtensionExceptionMessage.CUSTOM_EXTENSION_LIMIT_EXCEED;

public class CustomExtensionLimitExceedException extends ApplicationException {
    public CustomExtensionLimitExceedException() {
        super(CUSTOM_EXTENSION_LIMIT_EXCEED.getMessage());
    }
}
