package csed.swe.studentunity.Logic.Courses;


import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.DAO.RegisteredCourseRepo;
import csed.swe.studentunity.Logic.User.ActiveUserService;
import csed.swe.studentunity.Logic.User.UserService;
import csed.swe.studentunity.model.*;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Transactional
public class AllCourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;
    private final RegisteredCourseRepo registeredCourseRepo;
    private final ActiveCourseService activeCourseService;

    public AllCourseService(CourseRepo courseRepo, UserService userService, RegisteredCourseRepo registeredCourseRepo, ActiveCourseService activeCourseService) {
        this.courseRepo = courseRepo;
        this.userService = userService;
        this.registeredCourseRepo = registeredCourseRepo;
        this.activeCourseService = activeCourseService;
    }

    public ResponseEntity<String> addCourse(String sessionId, Course course){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can add courses", HttpStatus.FORBIDDEN);
        }
        if (course == null){
            return new ResponseEntity<>("couldn't add course", HttpStatus.CONFLICT);
        }
        courseRepo.save(course);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    public ResponseEntity<String> removeCourse(String sessionId, String code){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can delete courses", HttpStatus.FORBIDDEN);
        }
        courseRepo.deleteCourseByCode(code);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    public ResponseEntity<String> makeCourseActive(String sessionId, String code){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can change status", HttpStatus.FORBIDDEN);
        }
        Course course = courseRepo.findCourseByCode(code).orElse(null);
        if (course == null){
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (course.getActiveCourse() != null){
            return new ResponseEntity<>("Course is already active", HttpStatus.FAILED_DEPENDENCY);
        }
        ActiveCourse activeCourse = new ActiveCourse();
        activeCourse.setCourse(course);
        course.setActiveCourse(activeCourse);
        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> makeCourseInActive(String sessionId, String code){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can change status", HttpStatus.FORBIDDEN);
        }
        Course course = courseRepo.findCourseByCode(code).orElse(null);
        if (course == null){
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.NOT_FOUND);
        }
        ActiveCourse activeCourse = course.getActiveCourse();
        activeCourseService.deleteActiveCourse(course.getId());
        activeCourse = null;
        course.setActiveCourse(null);
        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
    }

    public Course getCourse(String code){
        Course course = courseRepo.findCourseByCode(code).orElseThrow(() -> new RuntimeException());
        return course;
    }

    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    public List<Course> getAllActiveCourses(){
        return courseRepo.findCourseByactiveCourseNotNull();
    }

    public List<Course> searchAllCourses(String search){
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Course course = new Course();
        course.setName(search);
        course.setCode(search);
        System.out.println(course);
        Example<Course> example = Example.of(course, exampleMatcher);
        return courseRepo.findAll(example);
    }

    public Object[] getRegisteredCourses(String sessionId){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null)
            return new Object[]{registeredCourseRepo.getRegisteredCourseByUserId(userId), 200};
        return new Object[]{new ArrayList<>(), 404};
    }

    public int registerCourse(String sessionId, long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String email = activeUserService.getEmailFromSessionId(UUID.fromString(sessionId));
        if (email == null)
            return 404;
        User user = userService.getUser(email).orElse(null);
        Course course = courseRepo.findCourseById(courseId).orElse(null);
        if (user != null && course != null) {
            registeredCourseRepo.save(new RegisteredCourse(course, user, true));
            return 200;
        }
        return 404;
    }

    public int unRegisterCourse(String sessionId, long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null) {
            registeredCourseRepo.unRegisteredCourse(courseId, userId);
            return 200;
        }
        return 404;
    }

    public int toggleRVSubscription(String sessionId, long courseId, boolean oldRevisionSubscription) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null) {
            registeredCourseRepo.updateRVSubscription(courseId, userId, !oldRevisionSubscription);
            return 200;
        }
        return 404;
    }

    public ResponseEntity<String> editCourseName(String sessionId, String name, String code){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can edit courses", HttpStatus.FORBIDDEN);
        }
        Course c = courseRepo.findCourseByCode(code).orElse(null);
        if (c == null){
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        c.setName(name);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    public ResponseEntity<String> editCourseCode(String sessionId, String new_code, String code){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can edit courses", HttpStatus.FORBIDDEN);
        }
        Course c = courseRepo.findCourseByCode(code).orElse(null);
        if (c == null){
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        c.setCode(new_code);
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    public ResponseEntity<String> editActiveCourse(String sessionId, String code, ActiveCourse activeCourse){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        if (!user.getRole().equals("admin")){
            return new ResponseEntity<>("Only admins can edit courses", HttpStatus.FORBIDDEN);
        }
        Course c = courseRepo.findCourseByCode(code).orElse(null);
        if (c == null){
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
        if (c.getActiveCourse() == null){
            return new ResponseEntity<>("course is not active", HttpStatus.FAILED_DEPENDENCY);
        }
        c.getActiveCourse().setTimeTable(activeCourse.getTimeTable());
        c.getActiveCourse().setTelegramLink(activeCourse.getTelegramLink());
        c.getActiveCourse().setNotificationsToken(activeCourse.getNotificationsToken());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    public Course getCourseById(long id) {
        Course course = courseRepo.findCourseById(id).orElseThrow(() -> new RuntimeException());
        return course;
    }

}
