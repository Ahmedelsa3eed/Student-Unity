package csed.swe.studentunity.Logic;


import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.model.Course;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class AllCourseService {

    @Autowired
    private final CourseRepo courseRepo;

    public AllCourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
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

    public void getSubscribedCourses(){

    }

    public void subscribe(){

    }

    public void unSubscribe(){

    }

    public void editCourse(){

    }

}
