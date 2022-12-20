package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.Tasks.StudentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/myTasks")
public class StudentTaskAPI {

    @Autowired
    private StudentTaskService studentTaskService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Object>> getTasks(@RequestParam("sessionId") String sessionId) {
        return new ResponseEntity<>(studentTaskService.getTasks(sessionId), HttpStatus.OK);
    }

    @GetMapping("/filterTasksByCourseCode")
    public ResponseEntity<Iterable<Object>> filterTasksByCourse(@RequestParam("sessionId") String sessionId,
                                                        @RequestParam("courseCode") String courseCode) {
        return new ResponseEntity<>(studentTaskService.filterTasksByCourse(sessionId, courseCode), HttpStatus.OK);
    }

    @GetMapping("/sortTasksByDate")
    public ResponseEntity<Iterable<Object>> sortTasksByDate(@RequestParam("sessionId") String sessionId) {
        return new ResponseEntity<>(studentTaskService.sortTasksByDate(sessionId), HttpStatus.OK);
    }

    @PutMapping("/triggerTaskStatus")
    public ResponseEntity<Boolean> markAsDone(@RequestParam("sessionId") String sessionId,
                                             @RequestParam("taskId") long taskId,
                                              @RequestParam("newStatus") boolean newStatus) {
        if(Boolean.TRUE.equals(studentTaskService.markAsDone(sessionId, taskId, newStatus)))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/removeTask")
    public ResponseEntity<Boolean> removeTask(@RequestParam("sessionId") String sessionId,
                                        @RequestParam("taskId") long taskId) {
        if(Boolean.TRUE.equals(studentTaskService.removeTask(sessionId, taskId)))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}


