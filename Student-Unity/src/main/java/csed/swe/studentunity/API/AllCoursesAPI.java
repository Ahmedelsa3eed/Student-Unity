package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.Courses.AllCourseService;
import csed.swe.studentunity.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/AllCourses")
public class AllCoursesAPI {

    private final AllCourseService allCourseService;

    public AllCoursesAPI(AllCourseService allCourseService) {
        this.allCourseService = allCourseService;
    }

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestParam String role, @RequestBody Course course){
        try{
            String added = allCourseService.addCourse(role, course);
            if (added.equals("Only admins can add courses")){
                return new ResponseEntity<>(added, HttpStatus.FORBIDDEN);
            }
            else{
                return new ResponseEntity<>(added, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>("Couldn't add course", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/removeCourse")
    public ResponseEntity<String> removeCourse(@RequestParam String role, @RequestParam String code){
        String removed = allCourseService.removeCourse(role, code);
        if (removed.equals("Only admins cant delete courses")){
            return new ResponseEntity<>(removed, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(removed, HttpStatus.OK);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam String role, @RequestParam String code, @RequestParam boolean status){
        try{
            String changed = allCourseService.changeCourseStatus(role, code, status);
            if (changed.equals("ok")){
                return new ResponseEntity<>(changed, HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<>(changed, HttpStatus.FORBIDDEN);
            }
        }
        catch (RuntimeException e){
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCourse")
    public ResponseEntity<Course> getCourse(@RequestParam String code){
        try{
            Course course = allCourseService.getCourse(code);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>(allCourseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/getAllActiveCourses")
    public ResponseEntity<List<Course>> getAllActiveCourses() {
        return new ResponseEntity<>(allCourseService.getAllActiveCourses(), HttpStatus.OK);
    }

    @GetMapping("/getRegisteredCourses/{sessionId}")
    public ResponseEntity<List<?>> getRegisteredCourses(@PathVariable("sessionId") String sessionId){
        Object[] result = allCourseService.getRegisteredCourses(sessionId);
        return new ResponseEntity<>((List<?>)result[0], HttpStatusCode.valueOf((int)result[1]));
    }

    @PutMapping("/registerCourse/{sessionId}/{courseId}")
    public ResponseEntity<?> registerCourse(@PathVariable("sessionId") String sessionId, @PathVariable("courseId") long courseId){
        return new ResponseEntity<>(HttpStatusCode.valueOf(allCourseService.registerCourse(sessionId, courseId)));
    }

    @DeleteMapping("/unRegisterCourse/{sessionId}/{courseId}")
    public ResponseEntity<?> unRegisterCourse(@PathVariable("sessionId") String sessionId, @PathVariable("courseId") long courseId){
        return new ResponseEntity<>(HttpStatusCode.valueOf(allCourseService.unRegisterCourse(sessionId, courseId)));
    }

}
