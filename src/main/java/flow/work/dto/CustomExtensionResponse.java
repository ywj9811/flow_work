package flow.work.dto;

import flow.work.entity.extension.CustomExtension;

import java.util.List;

public record CustomExtensionResponse(int size, List<Extension> extensions) {
    public record Extension(String extension, long extensionId) {}

    public static CustomExtensionResponse.Extension from(CustomExtension entity) {
        return new CustomExtensionResponse.Extension(
                entity.getName().toLowerCase(),
                entity.getCustomExtensionId()
        );
    }
}
