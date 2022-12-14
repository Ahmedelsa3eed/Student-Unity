package csed.swe.studentunity.API;


import csed.swe.studentunity.Logic.ActiveUserService;
import csed.swe.studentunity.Logic.StudentTaskService;
import csed.swe.studentunity.model.Task;
import csed.swe.studentunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/MyTasks")
public class StudentTaskAPI {

    @Autowired
    private StudentTaskService studentTaskService;

    @GetMapping("/getTasks")
    public ResponseEntity<Iterable<Object>> getTasks(@RequestParam("sessionId") String sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        return new ResponseEntity<>(studentTaskService.getTasks(userId) , HttpStatus.OK);
    }

    @GetMapping("/filterTasksByCourseCode")
    public ResponseEntity<Iterable<Object>> filterTasksByCourse(@RequestParam("sessionId") String sessionId,
                                                        @RequestParam("courseCode") String courseCode) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        return new ResponseEntity<>(studentTaskService.filterTasksByCourse(userId, courseCode) , HttpStatus.OK);
    }

    @GetMapping("/sortTacksByDate")
    public ResponseEntity<Iterable<Object>> sortTasksByDate(@RequestParam("sessionId") String sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        return new ResponseEntity<>(studentTaskService.sortTasksByDate(userId) , HttpStatus.OK);
    }


    @PutMapping("/markAsDone")
    public ResponseEntity<Boolean> markAsDone(@RequestParam("sessionId") String sessionId,
                                             @RequestParam("taskId") long taskId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long studentId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if( studentTaskService.markAsDone(studentId, taskId) )
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/removeTask")
    public ResponseEntity<Boolean> removeTask(@RequestParam("sessionId") String sessionId,
                                        @RequestParam("taskId") long taskId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        long studentId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if( studentTaskService.removeTask(studentId, taskId) )
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}


