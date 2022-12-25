package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialCategoryRepo extends JpaRepository<MaterialCategory, Long> {
}
