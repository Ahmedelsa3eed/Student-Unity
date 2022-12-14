package csed.swe.studentunity.Logic;


import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AllCourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;

    public AllCourseService(CourseRepo courseRepo, UserService userService) {
        this.courseRepo = courseRepo;
        this.userService = userService;
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

    public List<Course> getSubscribedCourses(String sessionId){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user != null)
            return user.getRegisteredCourses();
        return new ArrayList<>();
    }

    public void subscribe(){

    }

    public void unSubscribe(){

    }

    public void editCourse(){

    }

}
