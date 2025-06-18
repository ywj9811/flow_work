package flow.work.exception.extension;

import flow.work.exception.ApplicationException;

import static flow.work.exception.extension.message.ExtensionExceptionMessage.CUSTOM_EXTENSION_CONFLICT_WITH_FIXED;

public class CustomExtensionConflictWithFixedException extends ApplicationException {
    public CustomExtensionConflictWithFixedException() {
        super(CUSTOM_EXTENSION_CONFLICT_WITH_FIXED.getMessage());
    }
}
