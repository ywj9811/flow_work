package flow.work.extension.exception;

import flow.work.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.CUSTOM_EXTENSION_LIMIT_EXCEED;

public class CustomExtensionLimitExceedException extends ApplicationException {
    public CustomExtensionLimitExceedException() {
        super(CUSTOM_EXTENSION_LIMIT_EXCEED.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
