package flow.work.extension.service;

import flow.work.extension.dto.req.FixedExtensionUpdateRequest;
import flow.work.extension.dto.res.FixedExtensionResponse;
import flow.work.extension.dto.res.FixedExtensionResponse.Extension;
import flow.work.extension.mapper.ExtensionMapper;
import flow.work.extension.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class FixedExtensionService {
    private final FixedExtensionRepository fixedExtensionRepository;

    @Transactional(readOnly = true)
    public FixedExtensionResponse allFixedExtension() {
        List<Extension> extensions = fixedExtensionRepository.findAll().stream()
                .map(ExtensionMapper::toFixedExtension)
                .toList();

        return new FixedExtensionResponse(extensions);
    }

    public void updateExtension(FixedExtensionUpdateRequest request) {
        fixedExtensionRepository.findAll().forEach(
                extension -> extension.updateIsCheck(request.checkedIds().contains(extension.getFixedExtensionId()))
        );
    }

    public void clearExtension() {
        fixedExtensionRepository.findAll()
                .forEach(extension -> extension.updateNonCheck());
    }
}
