package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.RegisteredCourseId;
import csed.swe.studentunity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegisteredCourseRepo extends JpaRepository<RegisteredCourse, RegisteredCourseId> {

    @Query(value = "Select c.course_id, c.course_name, c.course_code, r.revision_subscription from course as c join " +
            "registered_course as r on c.course_id = r.course_id where r.id = ?1", nativeQuery=true)
    List<?> getRegisteredCourseByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM RegisteredCourse r WHERE r.course = ?1 AND r.user = ?2")
    void unRegisteredCourse(Course course, User user);

    @Modifying
    @Query(value = "update RegisteredCourse r set r.revisionSubscription = ?3 where r.course = ?1 and r.user = ?2")
    void updateRegisteredCourseRevisionSubscription(Course course, User user, Boolean newRevisionSubscription);

    Optional<RegisteredCourse> getRegisteredCourseByCourseId(long course_id);
}
