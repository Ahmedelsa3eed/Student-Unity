package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.ActiveCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveCourseRepo extends JpaRepository<ActiveCourse, Long> {
}
