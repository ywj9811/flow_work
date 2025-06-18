package flow.work.dto.res;

import flow.work.entity.extension.FixedExtension;

import java.util.List;

public record FixedExtensionResponse(List<Extension> extensions) {
    public record Extension(String extension, boolean isCheck, long id) {}
}
