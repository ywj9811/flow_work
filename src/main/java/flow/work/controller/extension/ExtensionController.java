package flow.work.controller.extension;

import flow.work.dto.res.CustomExtensionResponse;
import flow.work.dto.res.FixedExtensionResponse;
import flow.work.service.extension.CustomExtensionService;
import flow.work.service.extension.FixedExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExtensionController {
    private final FixedExtensionService fixedExtensionService;
    private final CustomExtensionService customExtensionService;

    @GetMapping("/")
    public String index(Model model) {
        FixedExtensionResponse fixedExtensionResponse = fixedExtensionService.allFixedExtension();
        CustomExtensionResponse customExtensionResponse = customExtensionService.allCustomExtension();
        model.addAttribute("fixedExtensionResponse", fixedExtensionResponse);
        model.addAttribute("customExtensionResponse", customExtensionResponse);
//        model.addAttribute("uploadedFiles", );
        //todo : 파일 관련해서 작업 필요
        return "index";
    }
}
