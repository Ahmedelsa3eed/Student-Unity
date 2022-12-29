package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NotificationRepo extends JpaRepository<Notification, Long> {


}
