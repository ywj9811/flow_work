package flow.work.uploadfile.mapper;

import flow.work.uploadfile.dto.res.UploadFileResponse.FileInfo;
import flow.work.uploadfile.entity.UploadFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UploadFileMapperTest {

    @Test
    @DisplayName("UploadFile -> DTO 변환")
    void toFileInfoDtoTest() {
        UploadFile uploadFile = UploadFile.builder()
                .fileId(100L)
                .name("test.pdf")
                .build();

        FileInfo response = UploadFileMapper.toFileInfo(uploadFile);

        assertThat(response.name()).isEqualTo("test.pdf");
        assertThat(response.id()).isEqualTo(100L);
    }

    @Test
    @DisplayName("파일명 -> UploadFile 생성")
    void toUploadFileEntityTest() {
        UploadFile uploadFile = UploadFileMapper.toUploadFile("abc.docx");

        assertThat(uploadFile.getName()).isEqualTo("abc.docx");
    }
}
