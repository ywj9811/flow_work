package flow.work.uploadfile.controller;

import flow.work.uploadfile.exception.BlockedExtensionException;
import flow.work.uploadfile.service.UploadFileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static flow.work.uploadfile.exception.message.UploadFileExceptionMessage.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UploadFileApiController.class)
public class UploadFileApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UploadFileService uploadFileService;

    @Test
    @DisplayName("파일 업로드 성공")
    void uploadFileSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test", "contentType", "file".getBytes());

        willDoNothing().given(uploadFileService)
                .addUploadFile(any());

        mockMvc.perform(multipart("/api/file").file(file))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("파일 삭제 성공")
    void deleteFile() throws Exception {
        long fileId = 1l;

        willDoNothing().given(uploadFileService).deleteUploadFile(fileId);

        mockMvc.perform(delete("/api/file/{id}", fileId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("차단된 확장자")
    void uploadFileBlockedExtension() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test", "contentType", "file".getBytes());

        willThrow(new BlockedExtensionException())
                .given(uploadFileService)
                .addUploadFile(any());

        mockMvc.perform(multipart("/api/file").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(BLOCKED_EXTENSION.getMessage()));
    }
}
