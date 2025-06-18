package flow.work.service.uploadfile;

import flow.work.dto.res.UploadFileResponse;
import flow.work.dto.res.UploadFileResponse.FileInfo;
import flow.work.entity.extension.FixedExtensionType;
import flow.work.entity.uploadfile.UploadFile;
import flow.work.mapper.UploadFileMapper;
import flow.work.repository.extension.CustomExtensionRepository;
import flow.work.repository.extension.FixedExtensionRepository;
import flow.work.repository.file.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UploadFileService {
    private final UploadFileRepository uploadFileRepository;
    private final CustomExtensionRepository customExtensionRepository;
    private final FixedExtensionRepository fixedExtensionRepository;

    public UploadFileResponse allUploadFile() {
        List<FileInfo> fileInfos = uploadFileRepository.findAll().stream()
                .map(UploadFileMapper::toFileInfo)
                .toList();

        return new UploadFileResponse(fileInfos);
    }

    public void addUploadFile(MultipartFile file) {
        String extension = extractExtension(file.getOriginalFilename());

        if (customExtensionRepository.existsByName(extension)) {
            throw new IllegalArgumentException("차단된 확장자가 포함되어 있습니다.");
        }

        FixedExtensionType.from(extension).ifPresent(value -> {
            if (fixedExtensionRepository.findByNameAndIsCheckTrue(value).isPresent()) {
                throw new IllegalArgumentException("차단된 확장자가 포함되어 있습니다.");
            }
        });

        UploadFile uploadFile = UploadFileMapper.toUploadFile(file.getOriginalFilename());
        uploadFileRepository.save(uploadFile);
    }

    private String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return ""; // 확장자 없음
        }

        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == filename.length() - 1) {
            return ""; // 파일명이 "."로 끝나는 경우 (예: "file.")
        }

        return filename.substring(lastDotIndex + 1).toUpperCase(); // 대문자로 반환 (e.g., "JPG")
    }

    public void deleteUploadFile(long id) {
        uploadFileRepository.deleteById(id);
    }
}
