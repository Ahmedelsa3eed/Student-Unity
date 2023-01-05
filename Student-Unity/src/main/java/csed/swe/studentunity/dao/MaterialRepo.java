package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepo extends JpaRepository<Material, Long> {

    @Query(value = "SELECT new Material(m.id, m.title, m.url, m.materialCategoryId) FROM Material m WHERE m.materialCategoryId = ?1")
    List<Material> getMaterialCategoryContent(Long materialCategoryId);

}
