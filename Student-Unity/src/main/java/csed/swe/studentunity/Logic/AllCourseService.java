package csed.swe.studentunity.Logic;


import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.DAO.RegisteredCourseRepository;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Transactional
public class AllCourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;
<<<<<<< HEAD
    private final RegisteredCourseRepository registeredCourseRepository;

    public AllCourseService(CourseRepo courseRepo, UserService userService, RegisteredCourseRepository registeredCourseRepository) {
        this.courseRepo = courseRepo;
        this.userService = userService;
        this.registeredCourseRepository = registeredCourseRepository;
=======

    public AllCourseService(CourseRepo courseRepo, UserService userService) {
        this.courseRepo = courseRepo;
        this.userService = userService;
>>>>>>> 8bea0af4a43977246d11f8b13733b989cbf80cb8
    }

    public String addCourse(String role, Course course){
        if (!role.equals("admin")){
            return "Only admins can add courses";
        }
        courseRepo.save(course);
        return "ok";
    }

    public String removeCourse(String role, String code){
        if (!role.equals("admin")){
            return "Only admins cant delete courses";
        }
        courseRepo.deleteCourseByCode(code);
        return "ok";
    }

    public String changeCourseStatus(String role, String code, boolean status){
        if (!role.equals("admin")){
            return "Only admins can change status";
        }
        Course course = courseRepo.findCourseByCode(code).orElseThrow(() -> new RuntimeException());
        course.setStatus(status);
        return "ok";
    }

    public Course getCourse(String code){
        Course course = courseRepo.findCourseByCode(code).orElseThrow(() -> new RuntimeException());
        return course;
    }

    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    public List<Course> getAllActiveCourses(){
        return courseRepo.findCourseByStatus(true);
    }

<<<<<<< HEAD
    public ResponseEntity<List<?>> getRegisteredCourses(String sessionId){
=======
    public ResponseEntity<Set<Course>> getRegisteredCourses(String sessionId){
>>>>>>> 8bea0af4a43977246d11f8b13733b989cbf80cb8
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user != null)
<<<<<<< HEAD
            return new ResponseEntity<>(registeredCourseRepository.getRegisteredCourseByUserId(user.getId()), HttpStatus.OK);
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public HttpStatusCode registerCourse(String sessionId, long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        Course course = courseRepo.findCourseById(courseId).orElse(null);
        if (user != null && course != null) {
            registeredCourseRepository.save(new RegisteredCourse(courseId, user.getId(), false));
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatusCode unRegisterCourse(String sessionId, long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        Course course = courseRepo.findCourseById(courseId).orElse(null);
        if (user != null && course != null) {
            registeredCourseRepository.deleteRegisteredCourseById(user.getId(), courseId);
            return HttpStatus.OK;
        }
=======
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.OK);
        return new ResponseEntity<>(new HashSet<>(), HttpStatus.NOT_FOUND);
    }

    public HttpStatusCode registerCourse(String sessionId, String courseCode) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        Course course = courseRepo.findCourseByCode(courseCode).orElse(null);
        // if (user != null && course != null)
            // Todo:
>>>>>>> 8bea0af4a43977246d11f8b13733b989cbf80cb8
        return HttpStatus.NOT_FOUND;
    }

    public void subscribe(){

    }

    public void unSubscribe(){

    }

    public void editCourse(){

    }

}
