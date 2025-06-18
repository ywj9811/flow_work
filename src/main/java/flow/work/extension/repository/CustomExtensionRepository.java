package flow.work.extension.repository;

import flow.work.extension.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    boolean existsByName(String name);
}
