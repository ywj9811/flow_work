package flow.work.extension.service;

import flow.work.extension.dto.req.FixedExtensionUpdateRequest;
import flow.work.extension.dto.res.FixedExtensionResponse;
import flow.work.extension.entity.FixedExtension;
import flow.work.extension.entity.FixedExtensionType;
import flow.work.extension.repository.FixedExtensionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FixedExtensionServiceTest {
    @Mock
    FixedExtensionRepository fixedExtensionRepository;

    @InjectMocks
    FixedExtensionService fixedExtensionService;

    @Test
    @DisplayName("전체 고정 확장자 조회")
    void allFixedExtensionTest() {
        FixedExtension bat = FixedExtension.builder()
                .fixedExtensionId(1L)
                .name(FixedExtensionType.BAT)
                .isCheck(true)
                .build();

        FixedExtension exe = FixedExtension.builder()
                .fixedExtensionId(2L)
                .name(FixedExtensionType.EXE)
                .isCheck(false)
                .build();

        given(fixedExtensionRepository.findAll()).willReturn(List.of(bat, exe));

        FixedExtensionResponse response = fixedExtensionService.allFixedExtension();

        assertThat(response.extensions().size()).isEqualTo(2);
    }


    @Test
    @DisplayName("고정 확장자 체크 상태 업데이트")
    void updateExtensionTest() {
        FixedExtension bat = FixedExtension.builder()
                .fixedExtensionId(1L)
                .isCheck(false)
                .build();

        FixedExtension exe = FixedExtension.builder()
                .fixedExtensionId(2L)
                .isCheck(false)
                .build();

        FixedExtension scr = FixedExtension.builder()
                .fixedExtensionId(3L)
                .isCheck(false)
                .build();

        List<Long> checkedIds = List.of(1L, 2L);
        FixedExtensionUpdateRequest request = new FixedExtensionUpdateRequest(checkedIds);

        given(fixedExtensionRepository.findAll()).willReturn(List.of(bat, exe, scr));

        fixedExtensionService.updateExtension(request);

        assertThat(bat.isCheck()).isTrue();
        assertThat(exe.isCheck()).isTrue();
        assertThat(scr.isCheck()).isFalse();
    }


    @Test
    @DisplayName("모든 고정 확장자 체크 해제")
    void clearExtensionTest() {
        FixedExtension bat = FixedExtension.builder()
                .fixedExtensionId(1L)
                .name(FixedExtensionType.BAT)
                .isCheck(true)
                .build();

        FixedExtension exe = FixedExtension.builder()
                .fixedExtensionId(2L)
                .name(FixedExtensionType.EXE)
                .isCheck(true)
                .build();

        given(fixedExtensionRepository.findAll()).willReturn(List.of(bat, exe));

        fixedExtensionService.clearExtension();

        assertThat(bat.isCheck()).isFalse();
        assertThat(exe.isCheck()).isFalse();
    }

}
