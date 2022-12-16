package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.ActiveCourse;
import csed.swe.studentunity.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveCourseRepo extends JpaRepository<ActiveCourse, Long> {
}
