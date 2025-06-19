package flow.work.uploadfile.service;

import flow.work.common.validation.ExtensionValidator;
import flow.work.uploadfile.dto.res.UploadFileResponse;
import flow.work.uploadfile.entity.UploadFile;
import flow.work.uploadfile.exception.BlockedExtensionException;
import flow.work.uploadfile.repository.UploadFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UploadFileServiceTest {
    @Mock
    private UploadFileRepository uploadFileRepository;

    @Mock
    private ExtensionValidator extensionValidator;

    @InjectMocks
    private UploadFileService uploadFileService;

    @Test
    @DisplayName("모든 업로드 파일 조회")
    void allUploadFileTest() {
        UploadFile file1 = UploadFile.builder().name("a.txt").build();
        UploadFile file2 = UploadFile.builder().name("b.txt").build();

        given(uploadFileRepository.findAll()).willReturn(List.of(file1, file2));

        UploadFileResponse response = uploadFileService.allUploadFile();

        assertThat(response.fileInfos().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("업로드 파일 등록")
    void addUploadFileTest() {
        MultipartFile mockFile = mock(MultipartFile.class);

        given(mockFile.getOriginalFilename()).willReturn("sample.txt");

        willDoNothing().given(extensionValidator)
                .validate("TXT");

        uploadFileService.addUploadFile(mockFile);

        then(uploadFileRepository).should().save(any(UploadFile.class));
    }

    @Test
    @DisplayName("업로드 파일 삭제")
    void deleteUploadFileTest() {
        uploadFileService.deleteUploadFile(1L);

        then(uploadFileRepository).should().deleteById(1L);
    }

    @Test
    @DisplayName("업로드 파일 등록 실패")
    void addUploadFileBlockedExtensionTest() {
        MultipartFile mockFile = mock(MultipartFile.class);

        given(mockFile.getOriginalFilename()).willReturn("danger.exe");

        willThrow(new BlockedExtensionException())
                .given(extensionValidator)
                 .validate("EXE");

        assertThatThrownBy(() -> uploadFileService.addUploadFile(mockFile))
                .isInstanceOf(BlockedExtensionException.class);
    }
}