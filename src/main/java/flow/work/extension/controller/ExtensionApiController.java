package flow.work.extension.controller;

import flow.work.extension.dto.req.CustomExtensionAddRequest;
import flow.work.extension.dto.req.FixedExtensionUpdateRequest;
import flow.work.extension.service.CustomExtensionService;
import flow.work.extension.service.FixedExtensionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Extension Controller")
public class ExtensionApiController {
    private final FixedExtensionService fixedExtensionService;
    private final CustomExtensionService customExtensionService;

    @PostMapping("/fixed-extensions")
    @Operation(summary = "고정 확장자 체크 혹은 해제")
    public ResponseEntity<Void> alterFixedExtension(@RequestBody FixedExtensionUpdateRequest request) {
        fixedExtensionService.updateExtension(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fixed-extensions/reset")
    @Operation(summary = "고정 확장자 체크 초기화")
    public ResponseEntity<Void> clearFixedExtension() {
        fixedExtensionService.clearExtension();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/custom-extensions")
    @Operation(summary = "커스텀 확장자 추가")
    public ResponseEntity<Void> addCustomExtension(@RequestBody CustomExtensionAddRequest request) {
        customExtensionService.addCustomExtension(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/custom-extensions/{id}")
    @Operation(summary = "커스텀 확장자 삭제")
    public ResponseEntity<Void> deleteCustomExtension(@PathVariable long id) {
        customExtensionService.deleteCustomExtension(id);
        return ResponseEntity.ok().build();
    }
}
