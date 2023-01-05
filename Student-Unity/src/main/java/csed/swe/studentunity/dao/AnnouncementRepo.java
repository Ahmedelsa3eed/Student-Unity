package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnnouncementRepo extends JpaRepository<Announcement, UUID> {

    @Query(value =
            "SELECT course_name, body, posted_date, announcement_id " +
            "FROM announcement AS a JOIN registered_course AS rc ON a.course_id = rc.course_id " +
                    "JOIN course AS c ON rc.course_id = c.course_id " +
            "WHERE rc.id = ?1", nativeQuery = true)
    Optional<Iterable<Object>> getAnnouncementByRegisteredCourse(Long userId);

    @Query(value =
            "SELECT course_name, body, posted_date, announcement_id " +
            "FROM announcement AS a JOIN registered_course AS rc ON a.course_id = rc.course_id " +
                    "JOIN course AS c ON rc.course_id = c.course_id " +
            "WHERE rc.id = ?1 AND a.course_id = ?2", nativeQuery = true)
    Optional<List<Object>> filterAnnouncements(Long userId, Long courseId);

    @Modifying
    @Query( value = "DELETE FROM announcement  WHERE announcement_id = :announcementId ", nativeQuery = true)
    void deleteById(Long announcementId);

    // edit the announcement body
    @Modifying
    @Query( value = "UPDATE announcement SET body = ?1 WHERE announcement_id = ?2", nativeQuery = true)
    void editAnnouncement(String body, Long announcementId);
}