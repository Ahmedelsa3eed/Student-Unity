package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.StudentTaskRepository;
import csed.swe.studentunity.DAO.UserRepository;
import csed.swe.studentunity.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StudentTaskService {

    private final StudentTaskRepository studentTaskRepository;
    @Autowired
    public StudentTaskService(StudentTaskRepository studentTaskRepository) {
        this.studentTaskRepository = studentTaskRepository;
    }

    public StudentTask saveStudentTask(StudentTask studentTask) {
        return studentTaskRepository.save(studentTask);
    }

    /**
     * blocked by subscribedCoursesService
     * */
    public void addTaskIdToAllSubscribedUsers(Task task) {
//        List<String> emails =  subscribedCoursesService.getAllSubscribesUsers(task.courseCode);
//        for (String email: emails)
//            saveStudentTask(new StudentTask(new StudentTaskId(email, task.getId()), false));
        System.out.println("task is added to subscribed users");
    }

    public Iterable<Object> getTasks(Long userId) {
        try{
            return studentTaskRepository.findAllStudentTasksByEmail(new User(userId));
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<Object> filterTasksByCourse(Long userId, String courseCode) {
        try{
            return studentTaskRepository.findAllStudentTasksByEmailAndCourseCode(new User(userId), new Course(courseCode));
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<Object> sortTasksByDate(Long userId) {
        try{
            return studentTaskRepository.sortTasksByDate(new User(userId));
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean markAsDone(Long studentId, long taskId) {
        try {
            studentTaskRepository.markAsDone(new User(studentId), new Task(taskId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeTask(long studentId, long taskId) {
        try {
            studentTaskRepository.removeTask(new User(studentId), new Task(taskId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
