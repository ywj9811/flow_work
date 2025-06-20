package flow.work.extension.entity;

import flow.work.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FixedExtension extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fixedExtensionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private FixedExtensionType name;

    @Column(nullable = false)
    private boolean isCheck;

    public void updateIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public void updateNonCheck() {
        this.isCheck = false;
    }
}
