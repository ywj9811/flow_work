package flow.work.dto;

import flow.work.entity.extension.FixedExtension;

import java.util.List;

public record FixedExtensionResponse(List<Extension> extensions) {
    public record Extension(String extension, boolean isCheck, long id) {}

    public static Extension from(FixedExtension entity) {
        return new Extension(
                entity.getName().name().toLowerCase(),
                entity.isCheck(),
                entity.getFixedExtensionId()
        );
    }
}
