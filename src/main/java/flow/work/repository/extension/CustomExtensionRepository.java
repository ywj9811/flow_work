package flow.work.repository.extension;

import flow.work.entity.extension.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    boolean existsByName(String name);
}
