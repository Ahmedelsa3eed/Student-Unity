package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepo extends JpaRepository<Material, Long> {
}
