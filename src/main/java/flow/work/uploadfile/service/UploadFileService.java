package flow.work.uploadfile.service;

import flow.work.uploadfile.dto.res.UploadFileResponse;
import flow.work.uploadfile.dto.res.UploadFileResponse.FileInfo;
import flow.work.uploadfile.entity.UploadFile;
import flow.work.uploadfile.mapper.UploadFileMapper;
import flow.work.uploadfile.repository.UploadFileRepository;
import flow.work.common.validation.ExtensionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UploadFileService {
    private final UploadFileRepository uploadFileRepository;
    private final ExtensionValidator extensionValidator;

    public UploadFileResponse allUploadFile() {
        List<FileInfo> fileInfos = uploadFileRepository.findAll().stream()
                .map(UploadFileMapper::toFileInfo)
                .toList();

        return new UploadFileResponse(fileInfos);
    }

    public void addUploadFile(MultipartFile file) {
        String extension = extractExtension(file.getOriginalFilename());
        extensionValidator.validate(extension);
        UploadFile uploadFile = UploadFileMapper.toUploadFile(file.getOriginalFilename());
        uploadFileRepository.save(uploadFile);
    }

    private String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }

        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == filename.length() - 1) {
            return "";
        }

        return filename.substring(lastDotIndex + 1).toUpperCase();
    }

    public void deleteUploadFile(long id) {
        uploadFileRepository.deleteById(id);
    }
}
