package flow.work.service.extension;

import flow.work.dto.req.CustomExtensionAddRequest;
import flow.work.dto.res.CustomExtensionResponse;
import flow.work.dto.res.CustomExtensionResponse.Extension;
import flow.work.entity.extension.CustomExtension;
import flow.work.mapper.ExtensionMapper;
import flow.work.repository.extension.CustomExtensionRepository;
import flow.work.validation.ExtensionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomExtensionService {
    private final CustomExtensionRepository customExtensionRepository;
    private final ExtensionValidator extensionValidator;

    @Transactional(readOnly = true)
    public CustomExtensionResponse allCustomExtension() {
        List<Extension> extensions = customExtensionRepository.findAll().stream()
                .map(ExtensionMapper::toCustomExtension)
                .sorted(Comparator.comparing(Extension::extension))
                .toList();

        return new CustomExtensionResponse(extensions.size(), extensions);
    }

    public void addCustomExtension(CustomExtensionAddRequest request) {
        String cleaned = sanitizeExtension(request);
        extensionValidator.validateCustomExtension(cleaned);

        CustomExtension customExtension = ExtensionMapper.toCustomExtension(cleaned);
        customExtensionRepository.save(customExtension);
    }

    private String sanitizeExtension(CustomExtensionAddRequest request) {
        return request.name()
                .replace(".", "")
                .replaceAll("\\s+", "")
                .toUpperCase();
    }

    public void deleteCustomExtension(long id) {
        customExtensionRepository.deleteById(id);
    }
}
