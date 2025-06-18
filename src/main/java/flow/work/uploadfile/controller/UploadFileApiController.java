package flow.work.uploadfile.controller;

import flow.work.uploadfile.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UploadFileApiController {
    private final UploadFileService uploadFileService;

    @PostMapping("/file")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        uploadFileService.addUploadFile(file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable long id) {
        uploadFileService.deleteUploadFile(id);
        return ResponseEntity.ok().build();
    }
}
