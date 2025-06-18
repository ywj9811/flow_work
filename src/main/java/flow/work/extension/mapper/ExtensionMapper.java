package flow.work.extension.mapper;

import flow.work.extension.dto.res.CustomExtensionResponse;
import flow.work.extension.dto.res.FixedExtensionResponse.Extension;
import flow.work.extension.entity.CustomExtension;
import flow.work.extension.entity.FixedExtension;

public class ExtensionMapper {
    public static CustomExtensionResponse.Extension toCustomExtension(CustomExtension entity) {
        return new CustomExtensionResponse.Extension(
                entity.getName().toLowerCase(),
                entity.getCustomExtensionId()
        );
    }

    public static Extension toFixedExtension(FixedExtension entity) {
        return new Extension(
                entity.getName().name().toLowerCase(),
                entity.isCheck(),
                entity.getFixedExtensionId()
        );
    }

    public static CustomExtension toCustomExtension(String name) {
        return CustomExtension.builder()
                .name(name)
                .build();
    }
}
