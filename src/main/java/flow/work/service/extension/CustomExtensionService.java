package flow.work.service.extension;

import flow.work.dto.CustomExtensionResponse;
import flow.work.dto.CustomExtensionResponse.Extension;
import flow.work.repository.extension.CustomExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomExtensionService {
    private final CustomExtensionRepository customExtensionRepository;

    public CustomExtensionResponse allCustomExtension() {
        List<Extension> extensions = customExtensionRepository.findAll().stream()
                .map(CustomExtensionResponse::from)
                .sorted(Comparator.comparing(Extension::extension))
                .toList();

        return new CustomExtensionResponse(extensions.size(), extensions);
    }
}
