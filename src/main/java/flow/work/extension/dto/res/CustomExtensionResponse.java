package flow.work.extension.dto.res;

import java.util.List;

public record CustomExtensionResponse(int size, List<Extension> extensions) {
    public record Extension(String extension, long extensionId) {}
}
