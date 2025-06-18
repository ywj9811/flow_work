package flow.work.dto.res;

import flow.work.entity.extension.CustomExtension;

import java.util.List;

public record CustomExtensionResponse(int size, List<Extension> extensions) {
    public record Extension(String extension, long extensionId) {}
}
