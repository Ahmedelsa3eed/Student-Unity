package csed.swe.studentunity.logic;

import csed.swe.studentunity.dao.AnnouncementRepo;
import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class AnnouncementService {

    private AnnouncementRepo announcementRepo;

    @Autowired
    public AnnouncementService(AnnouncementRepo announcementRepo) {
        this.announcementRepo = announcementRepo;
    }

    public Boolean addAnnouncement(Announcement announcement) {
        if (announcement == null)
            return false;
        this.announcementRepo.save(announcement);
        return true;
    }

    public Iterable<Object> getAnnouncements(String sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null)
            return this.announcementRepo.getAnnouncementByRegisteredCourse(userId)
                    .orElse(Collections.emptyList());
        return Collections.emptyList();
    }

    public Iterable<Object> filterAnnouncementsByCourse(String sessionId, Long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null)
            return this.announcementRepo.filterAnnouncements(userId, courseId)
                    .orElse(Collections.emptyList());
        return Collections.emptyList();
    }

    public Boolean deleteAnnouncement(String sessionId, Long announcementId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null) {
            this.announcementRepo.deleteById(announcementId);
            return true;
        }
        return false;
    }
}
