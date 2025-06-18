package flow.work.repository.extension;

import flow.work.entity.extension.FixedExtension;
import flow.work.entity.extension.FixedExtensionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, Long> {
    Optional<FixedExtension> findByNameAndIsCheckTrue(FixedExtensionType name);
}
