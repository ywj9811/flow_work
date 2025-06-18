package flow.work.extension.exception;

import flow.work.common.exception.ApplicationException;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.CUSTOM_EXTENSION_CONFLICT_WITH_FIXED;

public class CustomExtensionConflictWithFixedException extends ApplicationException {
    public CustomExtensionConflictWithFixedException() {
        super(CUSTOM_EXTENSION_CONFLICT_WITH_FIXED.getMessage());
    }
}
