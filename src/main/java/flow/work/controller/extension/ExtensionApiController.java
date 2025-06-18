package flow.work.controller.extension;

import flow.work.dto.req.CustomExtensionAddRequest;
import flow.work.dto.req.CustomExtensionDeleteRequest;
import flow.work.dto.req.FixedExtensionUpdateRequest;
import flow.work.service.extension.CustomExtensionService;
import flow.work.service.extension.FixedExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExtensionApiController {
    private final FixedExtensionService fixedExtensionService;
    private final CustomExtensionService customExtensionService;

    @PostMapping("/fixed-extensions")
    public ResponseEntity<Void> alterFixedExtension(@RequestBody FixedExtensionUpdateRequest request) {
        fixedExtensionService.updateExtension(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fixed-extensions/reset")
    public ResponseEntity<Void> clearFixedExtension() {
        fixedExtensionService.clearExtension();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/custom-extensions")
    public ResponseEntity<Void> addCustomExtension(@RequestBody CustomExtensionAddRequest request) {
        customExtensionService.addCustomExtension(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/custom-extensions/{id}")
    public ResponseEntity<Void> deleteCustomExtension(@PathVariable long id) {
        customExtensionService.deleteCustomExtension(id);
        return ResponseEntity.ok().build();
    }
}
