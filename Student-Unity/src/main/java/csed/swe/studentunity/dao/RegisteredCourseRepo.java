package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.RegisteredCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisteredCourseRepo extends JpaRepository<RegisteredCourse, RegisteredCourseId> {

    @Query(value = "Select c.course_id, c.course_name, c.course_code, r.revision_subscription from course as c join " +
            "registered_course as r on c.course_id = r.course_id where r.id = ?1", nativeQuery = true)
    List<?> getRegisteredCourseByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM registered_course WHERE course_id = ?1 AND id = ?2", nativeQuery = true)
    void unRegisteredCourse(Long courseId, Long userId);

    @Modifying
    @Query(value = "update registered_course set revision_subscription = ?3 where course_id = ?1 and id = ?2", nativeQuery = true)
    void updateRVSubscription(Long courseId, Long userId, Boolean newRevisionSubscription);

}
