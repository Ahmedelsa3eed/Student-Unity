package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo  extends JpaRepository<Course, String> {

    Optional<Course> findCourseByCode(String code);

}
