package flow.work.dto.res;

import java.time.LocalDateTime;
import java.util.List;

public record UploadFileResponse(List<FileInfo> fileInfos) {
    public record FileInfo(String name, LocalDateTime createdAt, long id) {}
}
