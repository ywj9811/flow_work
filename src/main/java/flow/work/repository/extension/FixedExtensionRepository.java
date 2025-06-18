package flow.work.repository.extension;

import flow.work.entity.extension.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, Long> {
    List<FixedExtension> findAllByIsCheckTrue();
}
