package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.RegisteredCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisteredCourseRepository extends JpaRepository<RegisteredCourse, RegisteredCourseId> {

    @Query(value = "Select new Course(c.id, c.name) from Course c where c.id in (select r.course.id from RegisteredCourse r where r.user.id = ?1)")
    List<?> getRegisteredCourseByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM RegisteredCourse r WHERE r.user.id = ?1 AND r.course.id = ?2")
    void unRegisteredCourseById(Long userId, Long courseId);

}
