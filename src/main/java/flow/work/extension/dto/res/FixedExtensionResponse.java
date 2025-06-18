package flow.work.extension.dto.res;

import java.util.List;

public record FixedExtensionResponse(List<Extension> extensions) {
    public record Extension(String extension, boolean isCheck, long id) {}
}
