package flow.work.extension.exception;

import flow.work.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.EXTENSION_LENGTH_EXCEED;

public class ExtensionLengthExceedException extends ApplicationException {
    public ExtensionLengthExceedException() {
        super(EXTENSION_LENGTH_EXCEED.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
