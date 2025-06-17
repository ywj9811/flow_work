package flow.work.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customExtensionType;

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
