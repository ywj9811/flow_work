package flow.work.service.extension;

import flow.work.dto.FixedExtensionResponse;
import flow.work.dto.FixedExtensionResponse.Extension;
import flow.work.repository.extension.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FixedExtensionService {
    private final FixedExtensionRepository fixedExtensionRepository;

    public FixedExtensionResponse allFixedExtension() {
        List<Extension> extensions = fixedExtensionRepository.findAll().stream()
                .map(FixedExtensionResponse::from)
                .toList();

        return new FixedExtensionResponse(extensions);
    }
}
