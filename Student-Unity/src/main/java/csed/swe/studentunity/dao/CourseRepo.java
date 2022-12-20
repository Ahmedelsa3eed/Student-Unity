package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, String> {

    void deleteCourseByCode(String code);

    Optional<Course> findCourseByCode(String code);

    Optional<Course> findCourseById(long id);

    List<Course> findCourseByactiveCourseNotNull();

}
