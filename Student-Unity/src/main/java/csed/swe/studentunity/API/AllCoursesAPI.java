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

    @PutMapping("/makeCourseActive")
    public ResponseEntity<String> makeCourseActive(@RequestParam String role, @RequestParam String code){
        try{
            String changed = allCourseService.makeCourseActive(role, code);
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

    @PutMapping("/makeCourseInActive")
    public ResponseEntity<String> makeCourseInActive(@RequestParam String role, @RequestParam String code){
        try{
            String changed = allCourseService.makeCourseInActive(role, code);
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

    @GetMapping("/searchAllCourses")
    public ResponseEntity<List<Course>> searchAllCourses(@RequestParam String search){
        System.out.println(search);
        return new ResponseEntity<>(allCourseService.searchAllCourses(search), HttpStatus.OK);
    }

    @PutMapping("/editCourseName")
    public ResponseEntity<String> editCourseName(@RequestParam String role, @RequestParam String name, @RequestParam String code){
        try {
            String returnValue = allCourseService.editCourseName(role, name, code);
            if (returnValue.equals("ok")){
                return new ResponseEntity<>(returnValue, HttpStatus.OK);
            }
            else if (returnValue.equals("Course not found"))
                return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity<>("Only admins can edit courses", HttpStatus.FORBIDDEN);
            }
        }
        catch (RuntimeException e){
            return new ResponseEntity<>("Name already exists", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/editCourseCode")
    public ResponseEntity<String> editCourseCode(@RequestParam String role, @RequestParam String new_code, @RequestParam String code){
        try {
            String returnValue = allCourseService.editCourseCode(role, new_code, code);
            if (returnValue.equals("ok")){
                return new ResponseEntity<>(returnValue, HttpStatus.OK);
            }
            else if (returnValue.equals("Course not found"))
                return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity<>("Only admins can edit courses", HttpStatus.FORBIDDEN);
            }
        }
        catch (RuntimeException e){
            return new ResponseEntity<>("Code already exists", HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/editActiveCourse")
    public ResponseEntity<String> editActiveCourse(@RequestParam String role, @RequestParam String code, @RequestBody ActiveCourse activeCourse){
        try {
            String returnValue = allCourseService.editActiveCourse(role, code, activeCourse);
            if (returnValue.equals("ok")){
                return new ResponseEntity<>(returnValue, HttpStatus.OK);
            }
            else if (returnValue.equals("Course not found"))
                return new ResponseEntity<>(returnValue, HttpStatus.NOT_FOUND);
            else if (returnValue.equals("Only admins can edit courses")) {
                return new ResponseEntity<>("Only admins can edit courses", HttpStatus.FORBIDDEN);
            }
            else{
                return new ResponseEntity<>("Course is not active", HttpStatus.FAILED_DEPENDENCY);
            }
        }
        catch (RuntimeException e){
            return new ResponseEntity<>("Error", HttpStatus.SEE_OTHER);
        }
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
