package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.RevisionNotification;
import csed.swe.studentunity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevisionNotificationRepo extends JpaRepository<RevisionNotification, Long> {
    Optional<RevisionNotification> findRevisionNotificationByUserId(Long userId);
}
