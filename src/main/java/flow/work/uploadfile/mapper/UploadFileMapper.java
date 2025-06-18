package flow.work.uploadfile.mapper;

import flow.work.uploadfile.dto.res.UploadFileResponse;
import flow.work.uploadfile.entity.UploadFile;

public class UploadFileMapper {
    public static UploadFileResponse.FileInfo toFileInfo(UploadFile uploadFile) {
        return new UploadFileResponse.FileInfo(uploadFile.getName(), uploadFile.getCreatedAt(), uploadFile.getFileId());
    }

    public static UploadFile toUploadFile(String fileName) {
        return UploadFile.builder()
                .name(fileName)
                .build();
    }
}
