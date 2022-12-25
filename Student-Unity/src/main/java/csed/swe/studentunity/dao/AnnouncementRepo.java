package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AnnouncementRepo extends JpaRepository<Announcement, UUID> {

    @Query(value =
            "SELECT course_name, body, posted_date " +
            "FROM announcement AS a JOIN registered_course AS rc ON a.course_id = rc.course_id " +
                    "JOIN course AS c ON rc.course_id = c.course_id " +
            "WHERE rc.id = ?1", nativeQuery = true)
    Optional<Iterable<Object>> getAnnouncementByRegisteredCourse(Long userId);
}