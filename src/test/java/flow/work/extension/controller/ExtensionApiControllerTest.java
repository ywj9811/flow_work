package flow.work.extension.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import flow.work.extension.dto.req.CustomExtensionAddRequest;
import flow.work.extension.dto.req.FixedExtensionUpdateRequest;
import flow.work.extension.exception.*;
import flow.work.extension.service.CustomExtensionService;
import flow.work.extension.service.FixedExtensionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static flow.work.extension.exception.message.ExtensionExceptionMessage.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExtensionApiController.class)
public class ExtensionApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    FixedExtensionService fixedExtensionService;

    @MockBean
    CustomExtensionService customExtensionService;

    @Test
    @DisplayName("고정 확장자 상태 변경")
    void alterFixedExtensionTest() throws Exception {
        FixedExtensionUpdateRequest dto = new FixedExtensionUpdateRequest(List.of(1l, 2l));
        String request = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/fixed-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("고정 확장자 초기화")
    void clearFixedExtensionTest() throws Exception {
        mockMvc.perform(post("/api/fixed-extensions/reset"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("커스텀 확장자 추가")
    void addCustomExtensionTest() throws Exception {
        CustomExtensionAddRequest dto = new CustomExtensionAddRequest("exe");
        String request = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/custom-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("커스텀 확장자 삭제")
    void deleteCustomExtensionTest() throws Exception {
        mockMvc.perform(delete("/api/custom-extensions/10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("형식 오류")
    void addCustomExtensionInvalidFormat() throws Exception {
        CustomExtensionAddRequest dto = new CustomExtensionAddRequest("@@!!");
        String request = objectMapper.writeValueAsString(dto);

        willThrow(new InvalidExtensionFormatException())
                .given(customExtensionService)
                .addCustomExtension(any());

        mockMvc.perform(post("/api/custom-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(INVALID_EXTENSION_FORMAT.getMessage()));
    }

    @Test
    @DisplayName("중복 확장자")
    void addCustomExtensionDuplicate() throws Exception {
        CustomExtensionAddRequest dto = new CustomExtensionAddRequest("exe");
        String request = objectMapper.writeValueAsString(dto);

        willThrow(new CustomExtensionAlreadyException())
                .given(customExtensionService)
                .addCustomExtension(any());

        mockMvc.perform(post("/api/custom-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(CUSTOM_EXTENSION_ALREADY.getMessage()));
    }

    @Test
    @DisplayName("고정 확장자와 중복")
    void addCustomExtensionConflictWithFixed() throws Exception {
        CustomExtensionAddRequest dto = new CustomExtensionAddRequest("bat");
        String request = objectMapper.writeValueAsString(dto);

        willThrow(new CustomExtensionConflictWithFixedException())
                .given(customExtensionService)
                .addCustomExtension(any());

        mockMvc.perform(post("/api/custom-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(CUSTOM_EXTENSION_CONFLICT_WITH_FIXED.getMessage()));
    }

    @Test
    @DisplayName("개수 초과")
    void addCustomExtension_LimitExceeded() throws Exception {
        CustomExtensionAddRequest dto = new CustomExtensionAddRequest("newext");
        String request = objectMapper.writeValueAsString(dto);

        willThrow(new CustomExtensionLimitExceedException())
                .given(customExtensionService)
                .addCustomExtension(any());

        mockMvc.perform(post("/api/custom-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(CUSTOM_EXTENSION_LIMIT_EXCEED.getMessage()));
    }

    @Test
    @DisplayName("길이 초과")
    void addCustomExtensionLengthExceeded() throws Exception {
        String tooLong = "X".repeat(21);
        CustomExtensionAddRequest dto = new CustomExtensionAddRequest(tooLong);
        String request = objectMapper.writeValueAsString(dto);

        willThrow(new ExtensionLengthExceedException())
                .given(customExtensionService)
                .addCustomExtension(any());

        mockMvc.perform(post("/api/custom-extensions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(EXTENSION_LENGTH_EXCEED.getMessage()));
    }

}
