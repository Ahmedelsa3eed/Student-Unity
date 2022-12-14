package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.CourseService;
import csed.swe.studentunity.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "course")
public class CourseAPI {

    private final CourseService courseService;

    public CourseAPI(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/getUserRegisteredCourses/{sessionId}")
    public ResponseEntity<List<Course>> getUserRegisteredCourses(@PathVariable String sessionId) {
        return new ResponseEntity<>(courseService.getUserRegisteredCourses(sessionId), HttpStatus.OK);
    }

    @GetMapping("/getCourseByCode")
    public ResponseEntity<Course> getCourseByCode(@RequestParam String code) {
        return new ResponseEntity<>(courseService.getCourseByCode(code).orElse(null), HttpStatus.OK);
    }

}
