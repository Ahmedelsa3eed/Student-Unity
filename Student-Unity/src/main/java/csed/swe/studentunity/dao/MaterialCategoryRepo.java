package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialCategoryRepo extends JpaRepository<MaterialCategory, Long> {

    @Query(value = "SELECT new MaterialCategory(mc.id, mc.courseId, mc.name) FROM MaterialCategory mc WHERE mc.courseId = ?1")
    List<MaterialCategory> getCourseMaterialCategories(Long courseId);

}
