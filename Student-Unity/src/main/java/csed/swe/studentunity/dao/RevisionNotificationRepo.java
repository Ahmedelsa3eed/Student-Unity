package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.RevisionNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevisionNotificationRepo extends JpaRepository<RevisionNotification, Long> {
    Optional<RevisionNotification> findRevisionNotificationByUserId(Long userId);
}
