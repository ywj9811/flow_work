package flow.work.controller.uploadfile;

import flow.work.service.uploadfile.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
