package csed.swe.studentunity.courses;

import csed.swe.studentunity.logic.courses.AllCourseService;
import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AllCourseServiceTest {

    @Autowired
    private AllCourseService allCourseService;

    @Autowired
    private UserService userService;


    @Test
    public void addCorrectCourse(){
        User adminUser = new User("coursetest1@test.com", 1, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course();
        course.setCode("test1code");
        course.setName("course1name");
        String testRes = allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course).getBody();
        assert testRes.equals("ok");
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test1code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void duplicateCode(){
        User adminUser = new User("coursetest2@test.com", 2, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course();
        course.setName("test2name");
        course.setCode("test2code");
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        Course course1 = new Course();
        course1.setName("name");
        course1.setCode("test2code");
        String res;
        try {
            res = allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course1).getBody();
        }
        catch (RuntimeException e){
            res = "couldn't add course";
        }
        assert res.equals("couldn't add course");
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test2code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void duplicateName(){
        User adminUser = new User("coursetest3@test.com", 3, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course();
        course.setName("test3name");
        course.setCode("test3code");
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        Course dup = new Course("test3codes");
        dup.setName("test3name");
        String res;
        try {
            res = allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), dup).getBody();
        }
        catch (Exception e){
            res = "couldn't add course";
        }
        assert res.equals("couldn't add course");
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test3code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void nullCourse(){
        User adminUser = new User("coursetest4@test.com", 4, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = null;
        String res = allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course).getBody();
        assert res.equals("couldn't add course");
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test1code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void nonAdminAdd(){
        User adminUser = new User("coursetest5@test.com", 5, "first name", "last name", "password", "student");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course("testcode");
        String res = allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course).getBody();
        assert res.equals("Only admins can add courses");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void removeCourse(){
        User adminUser = new User("coursetest6@test.com", 6, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course("test10code");
        course.setName("test10name");
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        String res = allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test10code").getBody();
        assert res.equals("ok");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void nonAdminRemoval(){
        User adminUser = new User("coursetest95@test.com", 95, "first name", "last name", "password", "student");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        String res = allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test11code").getBody();
        assert res.equals("Only admins can delete courses");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void makeCourseActive(){
        User adminUser = new User("coursetest97@test.com", 97, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course("test12code");
        course.setName("test12name");
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        String res = allCourseService.makeCourseActive(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test12code").getBody();
        assert res.equals("ok");
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test12code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void nonExistCourse(){
        User adminUser = new User("coursetest98@test.com", 98, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        String res = allCourseService.makeCourseActive(activeUserService.getSessionIdAsString(adminUser.getEmail()), "testcode").getBody();
        assert res.equals("Course doesn't exist");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void nonAdminMakeActive(){
        User adminUser = new User("coursetest99@test.com", 99, "first name", "last name", "password", "student");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course("test13code");
        course.setName("test13name");
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        String res = allCourseService.makeCourseActive(activeUserService.getSessionIdAsString(adminUser.getEmail()), "testcode").getBody();
        assert res.equals("Only admins can change status");
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test13code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void getCourseExists(){
        User adminUser = new User("coursetest100@test.com", 100, "first name", "last name", "password", "admin");
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        Course course = new Course("test14code");
        course.setName("test14name");
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        Course course1 = allCourseService.getCourse("test14code");
        assert course.getId() == course1.getId();
        assert course.getCode().equals(course1.getCode());
        assert course.getName().equals(course1.getName());
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), "test14code");
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    public void getCourseDoesnotExist(){

        String res;
        try{
            allCourseService.getCourse("test15code");
            res = "ok";
        }
        catch (RuntimeException e){
            res = "course does not exist";
        }
        assert res.equals("course does not exist");
    }

}