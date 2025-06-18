package flow.work.extension.controller;

import flow.work.extension.dto.res.CustomExtensionResponse;
import flow.work.extension.dto.res.FixedExtensionResponse;
import flow.work.uploadfile.dto.res.UploadFileResponse;
import flow.work.extension.service.CustomExtensionService;
import flow.work.extension.service.FixedExtensionService;
import flow.work.uploadfile.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExtensionController {
    private final FixedExtensionService fixedExtensionService;
    private final CustomExtensionService customExtensionService;
    private final UploadFileService uploadFileService;

    @GetMapping("/")
    public String index(Model model) {
        FixedExtensionResponse fixedExtensionResponse = fixedExtensionService.allFixedExtension();
        CustomExtensionResponse customExtensionResponse = customExtensionService.allCustomExtension();
        UploadFileResponse uploadFileResponse = uploadFileService.allUploadFile();
        model.addAttribute("fixedExtensionResponse", fixedExtensionResponse);
        model.addAttribute("customExtensionResponse", customExtensionResponse);
        model.addAttribute("uploadFileResponse", uploadFileResponse);
        return "index";
    }
}
