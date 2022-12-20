package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.Courses.AllCourseService;
import csed.swe.studentunity.model.ActiveCourse;
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
    public ResponseEntity<String> addCourse(@RequestParam String sessionId, @RequestBody Course course){
        try {
            return allCourseService.addCourse(sessionId, course);
        }
        catch (Exception e){
            return new ResponseEntity<>("Couldn't add course", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/removeCourse")
    public ResponseEntity<String> removeCourse(@RequestParam String sessionId, @RequestParam String code){
        return allCourseService.removeCourse(sessionId, code);
    }

    @PutMapping("/makeCourseActive")
    public ResponseEntity<String> makeCourseActive(@RequestParam String sessionId, @RequestParam String code){
        return allCourseService.makeCourseActive(sessionId, code);
    }

    @PutMapping("/makeCourseInActive")
    public ResponseEntity<String> makeCourseInActive(@RequestParam String sessionId, @RequestParam String code){
        return allCourseService.makeCourseInActive(sessionId, code);
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

    @GetMapping("/searchAllCourses")
    public ResponseEntity<List<Course>> searchAllCourses(@RequestParam String search){
        System.out.println(search);
        return new ResponseEntity<>(allCourseService.searchAllCourses(search), HttpStatus.OK);
    }

    @PutMapping("/editCourseName")
    public ResponseEntity<String> editCourseName(@RequestParam String sessionId, @RequestParam String name, @RequestParam String code){
        try {
            return allCourseService.editCourseName(sessionId, name, code);
        }
        catch (Exception e){
            return new ResponseEntity<>("Name already exists", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/editCourseCode")
    public ResponseEntity<String> editCourseCode(@RequestParam String sessionId, @RequestParam String new_code, @RequestParam String code){
        try {
            return allCourseService.editCourseCode(sessionId, new_code, code);
        }
        catch (Exception e){
            return new ResponseEntity<>("Code already exists", HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/editActiveCourse")
    public ResponseEntity<String> editActiveCourse(@RequestParam String sessionId, @RequestParam String code, @RequestBody ActiveCourse activeCourse){
        return allCourseService.editActiveCourse(sessionId, code, activeCourse);
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

    @PutMapping("/toggleRVSubscription")
    public ResponseEntity<?> toggleRVSubscription(@RequestParam("sessionId") String sessionId, @RequestParam("courseId") long courseId,
                                                  @RequestParam("oldRevisionSubscription") boolean oldRevisionSubscription){
        return new ResponseEntity<>(HttpStatusCode.valueOf(allCourseService.toggleRVSubscription(sessionId, courseId, oldRevisionSubscription)));
    }

}
