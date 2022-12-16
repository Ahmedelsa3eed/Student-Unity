package csed.swe.studentunity.Logic.Courses;


import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.DAO.RegisteredCourseRepository;
import csed.swe.studentunity.Logic.User.ActiveUserService;
import csed.swe.studentunity.Logic.User.UserService;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Transactional
public class AllCourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;
    private final RegisteredCourseRepository registeredCourseRepository;

    public AllCourseService(CourseRepo courseRepo, UserService userService, RegisteredCourseRepository registeredCourseRepository) {
        this.courseRepo = courseRepo;
        this.userService = userService;
        this.registeredCourseRepository = registeredCourseRepository;
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

    public Object[] getRegisteredCourses(String sessionId){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user != null)
            return new Object[]{registeredCourseRepository.getRegisteredCourseByUserId(user.getId()), 200};
        return new Object[]{new ArrayList<>(), 404};
    }

    public int registerCourse(String sessionId, long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        Course course = courseRepo.findCourseById(courseId).orElse(null);
        if (user != null && course != null) {
            registeredCourseRepository.save(new RegisteredCourse(course, user, false));
            return 200;
        }
        return 404;
    }

    public int unRegisterCourse(String sessionId, long courseId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        Course course = courseRepo.findCourseById(courseId).orElse(null);
        if (user != null && course != null) {
            registeredCourseRepository.deleteRegisteredCourseById(user.getId(), courseId);
            return 200;
        }
        return 404;
    }

    public void subscribe(){

    }

    public void unSubscribe(){

    }

    public void editCourse(){

    }

}
