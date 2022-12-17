package csed.swe.studentunity.Logic.Courses;


import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.DAO.RegisteredCourseRepository;
import csed.swe.studentunity.Logic.User.ActiveUserService;
import csed.swe.studentunity.Logic.User.UserService;
import csed.swe.studentunity.model.ActiveCourse;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.RegisteredCourse;
import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Transactional
public class AllCourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;
    private final RegisteredCourseRepository registeredCourseRepository;
    private final ActiveCourseService activeCourseService;

    public AllCourseService(CourseRepo courseRepo, UserService userService, RegisteredCourseRepository registeredCourseRepository, ActiveCourseService activeCourseService) {
        this.courseRepo = courseRepo;
        this.userService = userService;
        this.registeredCourseRepository = registeredCourseRepository;
        this.activeCourseService = activeCourseService;
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

    public String makeCourseActive(String role, String code){
        if (!role.equals("admin")){
            return "Only admins can change status";
        }
        Course course = courseRepo.findCourseByCode(code).orElseThrow(() -> new RuntimeException());
        ActiveCourse activeCourse = new ActiveCourse();
        activeCourse.setCourse(course);
        course.setActiveCourse(activeCourse);
        return "ok";
    }

    public String makeCourseInActive(String role, String code){
        Course course = courseRepo.findCourseByCode(code).orElseThrow(() -> new RuntimeException());
        ActiveCourse activeCourse = course.getActiveCourse();
        activeCourseService.deleteActiveCourse(course.getId());
        activeCourse = null;
        course.setActiveCourse(null);
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
            registeredCourseRepository.save(new RegisteredCourse(course, user, true));
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

    public String editCourseName(String role, String name, String code){

        if (!role.equals("admin")){
            return "Only admins can edit courses";
        }
        Course c = courseRepo.findCourseByCode(code).orElse(null);
        if (c == null){
            return "Course not found";
        }
        c.setName(name);
        return "ok";
    }
    public String editCourseCode(String role, String new_code, String code){
        if (!role.equals("admin")){
            return "Only admins can edit courses";
        }
        Course c = courseRepo.findCourseByCode(code).orElse(null);
        if (c == null){
            return "Course not found";
        }
        c.setCode(new_code);
        return "ok";
    }

    public String editActiveCourse(String role, String code, ActiveCourse activeCourse){
        if (!role.equals("admin")){
            return "Only admins can edit courses";
        }
        Course c = courseRepo.findCourseByCode(code).orElse(null);
        if (c == null){
            return "Course not found";
        }
        if (c.getActiveCourse() == null){
            return "course is not active";
        }
        c.getActiveCourse().setTimeTable(activeCourse.getTimeTable());
        c.getActiveCourse().setTelegramLink(activeCourse.getTelegramLink());
        c.getActiveCourse().setNotificationsToken(activeCourse.getNotificationsToken());
        return "ok";
    }

}
