package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.CourseRepo;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;

    @Autowired
    public CourseService(CourseRepo courseRepo, UserService userService) {
        this.courseRepo = courseRepo;
        this.userService = userService;
    }

    public List<Course> getUserRegisteredCourses(String sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String userEmail = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(userEmail).orElse(null);
        if (user != null)
            return user.getRegisteredCourses();
        return new ArrayList<>();
    }

    public Optional<Course> getCourseByCode(String code) {
        return courseRepo.findCourseByCode(code);
    }

}
