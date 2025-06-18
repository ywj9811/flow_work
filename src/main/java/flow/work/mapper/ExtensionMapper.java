package flow.work.mapper;

import flow.work.dto.req.CustomExtensionAddRequest;
import flow.work.dto.res.CustomExtensionResponse;
import flow.work.dto.res.FixedExtensionResponse.Extension;
import flow.work.entity.extension.CustomExtension;
import flow.work.entity.extension.FixedExtension;

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
