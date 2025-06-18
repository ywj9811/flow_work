package flow.work.exception.uploadfile;

import flow.work.exception.ApplicationException;

import static flow.work.exception.uploadfile.message.UploadFileExceptionMessage.BLOCKED_EXTENSION;

public class BlockedExtensionException extends ApplicationException {
    public BlockedExtensionException() {
        super(BLOCKED_EXTENSION.getMessage());
    }
}
