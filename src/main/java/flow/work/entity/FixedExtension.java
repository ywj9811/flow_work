package flow.work.entity;

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
public class FixedExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fixedExtensionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FixedExtensionType name;

    @Column(nullable = false)
    private boolean isCheck;
}
