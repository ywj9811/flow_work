package flow.work.service.extension;

import flow.work.dto.req.CustomExtensionAddRequest;
import flow.work.dto.req.CustomExtensionDeleteRequest;
import flow.work.dto.res.CustomExtensionResponse;
import flow.work.dto.res.CustomExtensionResponse.Extension;
import flow.work.entity.extension.CustomExtension;
import flow.work.entity.extension.FixedExtensionType;
import flow.work.mapper.ExtensionMapper;
import flow.work.repository.extension.CustomExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomExtensionService {
    private final CustomExtensionRepository customExtensionRepository;

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
        validateCustomExtension(cleaned);

        CustomExtension customExtension = ExtensionMapper.toCustomExtension(cleaned);
        customExtensionRepository.save(customExtension);
    }

    private String sanitizeExtension(CustomExtensionAddRequest request) {
        String cleaned = request.name()
                .replace(".", "")
                .replaceAll("\\s+", "")
                .toUpperCase();
        return cleaned;
    }

    private void validateCustomExtension(String cleaned) {
        if (cleaned.length() > 20) {
            throw new IllegalArgumentException("확장자는 최대 20자까지 가능합니다.");
        }

        if (!cleaned.matches("^[A-Z]+$")) {
            throw new IllegalArgumentException("확장자는 알파벳만 입력 가능합니다.");
        }

        if (customExtensionRepository.count() >= 200) {
            throw new IllegalStateException("최대 200개의 확장자만 등록할 수 있습니다.");
        }

        if (customExtensionRepository.existsByName(cleaned)) {
            throw new IllegalArgumentException("이미 등록된 확장자입니다.");
        }

        if (Arrays.stream(FixedExtensionType.values())
                .anyMatch(type -> type.name().equalsIgnoreCase(cleaned))) {
            throw new IllegalArgumentException("고정 확장자와 중복될 수 없습니다.");
        }
    }

    public void deleteCustomExtension(CustomExtensionDeleteRequest request) {
        customExtensionRepository.deleteById(request.id());
    }
}
