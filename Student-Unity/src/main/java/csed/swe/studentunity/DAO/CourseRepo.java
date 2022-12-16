package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, String> {

    void deleteCourseByCode(String code);

    Optional<Course> findCourseByCode(String code);
    @Query(value = "SELECT * FROM course WHERE id = ?1", nativeQuery = true)
    Optional<Course> findCourseById(long id);

    List<Course> findCourseByStatus(boolean status);

}
