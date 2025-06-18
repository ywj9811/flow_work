package flow.work.uploadfile.repository;

import flow.work.uploadfile.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
