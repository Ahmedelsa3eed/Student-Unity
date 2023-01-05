package csed.swe.studentunity.api;

import csed.swe.studentunity.logic.AnnouncementService;
import csed.swe.studentunity.model.Announcement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/announcement")
public class AnnouncementAPI {

    private final AnnouncementService announcementService;

    public AnnouncementAPI(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addAnnouncement(@RequestBody Announcement announcement) {
        Boolean res = this.announcementService.addAnnouncement(announcement);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Object>> getAnnouncements(@RequestParam("sessionId") String sessionId) {
        return new ResponseEntity<>(this.announcementService.getAnnouncements(sessionId), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Iterable<Object>> getAnnouncementsByCourse(@RequestParam("sessionId") String sessionId, @RequestParam("courseId") Long courseId) {
        return new ResponseEntity<>(this.announcementService.filterAnnouncementsByCourse(sessionId, courseId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAnnouncement(@RequestParam("sessionId") String sessionId, @RequestParam("announcementId") Long announcementId) {
        return new ResponseEntity<>(this.announcementService.deleteAnnouncement(sessionId, announcementId), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Boolean> editAnnouncement(@RequestParam("sessionId") String sessionId, @RequestBody Announcement announcement) {
        return new ResponseEntity<>(this.announcementService.editAnnouncement(sessionId, announcement), HttpStatus.OK);
    }

}
