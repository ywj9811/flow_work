package flow.work.extension.repository;

import flow.work.extension.entity.FixedExtension;
import flow.work.extension.entity.FixedExtensionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, Long> {
    Optional<FixedExtension> findByNameAndIsCheckTrue(FixedExtensionType name);
}
