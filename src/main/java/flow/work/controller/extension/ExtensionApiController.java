package flow.work.controller.extension;

import flow.work.dto.req.FixedExtensionUpdateRequest;
import flow.work.service.extension.FixedExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExtensionApiController {
    private final FixedExtensionService fixedExtensionService;

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
}
