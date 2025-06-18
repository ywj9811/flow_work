package flow.work.uploadfile.controller;

import flow.work.uploadfile.service.UploadFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Extension Controller")
public class UploadFileApiController {
    private final UploadFileService uploadFileService;

    @PostMapping("/file")
    @Operation(summary = "파일 업로드")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        uploadFileService.addUploadFile(file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file/{id}")
    @Operation(summary = "파일 삭제")
    public ResponseEntity<Void> deleteFile(@PathVariable long id) {
        uploadFileService.deleteUploadFile(id);
        return ResponseEntity.ok().build();
    }
}
