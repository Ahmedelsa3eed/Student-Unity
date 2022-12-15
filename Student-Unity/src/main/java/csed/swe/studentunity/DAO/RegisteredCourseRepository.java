package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.RegisteredCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredCourseRepository extends JpaRepository<RegisteredCourse, RegisteredCourseId> {
}
