package csed.swe.studentunity.dao;

public interface AnnouncementRepo extends org.springframework.data.jpa.repository.JpaRepository<csed.swe.studentunity.model.Announcement, java.util.UUID> ,org.springframework.data.jpa.repository.JpaSpecificationExecutor<csed.swe.studentunity.model.Announcement> {
}