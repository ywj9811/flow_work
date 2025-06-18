package flow.work.mapper;

import flow.work.dto.res.UploadFileResponse;
import flow.work.entity.uploadfile.UploadFile;

public class UploadFileMapper {
    public static UploadFileResponse.FileInfo toFileInfo(UploadFile uploadFile) {
        return new UploadFileResponse.FileInfo(uploadFile.getName(), uploadFile.getFileId());
    }
}
