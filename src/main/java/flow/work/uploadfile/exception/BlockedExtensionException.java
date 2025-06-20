package flow.work.uploadfile.exception;

import flow.work.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import static flow.work.uploadfile.exception.message.UploadFileExceptionMessage.BLOCKED_EXTENSION;

public class BlockedExtensionException extends ApplicationException {
    public BlockedExtensionException() {
        super(BLOCKED_EXTENSION.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
