package flow.work.extension.mapper;

import flow.work.extension.dto.res.CustomExtensionResponse;
import flow.work.extension.dto.res.FixedExtensionResponse;
import flow.work.extension.entity.CustomExtension;
import flow.work.extension.entity.FixedExtension;
import flow.work.extension.entity.FixedExtensionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExtensionMapperTest {
    @Test
    @DisplayName("CustomExtension -> DTO 변환")
    void toCustomExtensionDtoTest() {
        CustomExtension entity = CustomExtension.builder()
                .customExtensionId(1L)
                .name("EXE")
                .build();

        CustomExtensionResponse.Extension response = ExtensionMapper.toCustomExtension(entity);

        assertThat(response.extension()).isEqualTo("exe");
        assertThat(response.extensionId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("FixedExtension -> DTO 변환")
    void toFixedExtensionDtoTest() {
        FixedExtension extension = FixedExtension.builder()
                .fixedExtensionId(2L)
                .isCheck(true)
                .name(FixedExtensionType.EXE)
                .build();

        FixedExtensionResponse.Extension response = ExtensionMapper.toFixedExtension(extension);

        assertThat(response.extension()).isEqualTo("exe");
        assertThat(response.isCheck()).isTrue();
        assertThat(response.id()).isEqualTo(2L);
    }

    @Test
    @DisplayName("String -> CustomExtension 변환")
    void toCustomExtensionEntityTest() {
        CustomExtension extension = ExtensionMapper.toCustomExtension("BAT");

        assertThat(extension.getName()).isEqualTo("BAT");
    }
}
