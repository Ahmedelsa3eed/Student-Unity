package csed.swe.studentunity.Logic.Tasks;

import csed.swe.studentunity.DAO.StudentTaskRepository;
import csed.swe.studentunity.Logic.User.ActiveUserService;
import csed.swe.studentunity.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

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

    public void addTaskIdToAllSubscribedUsers(Task task) {
        this.studentTaskRepository.addTaskIdToAllSubscribedUsers(task.getTaskId(), task.getCourse().getCode());
    }

    public Iterable<Object> getTasks(String sessionId) {
        try{
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            Optional<Iterable<Object>> studentTasks =  studentTaskRepository.findAllStudentTasks(userId);
            return studentTasks.orElse(Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Iterable<Object> filterTasksByCourse(String sessionId, String courseCode) {
        try{
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            Optional<Iterable<Object>> studentTasks =  studentTaskRepository.filterStudentTasksByCourse(userId, courseCode);
            return studentTasks.orElse(Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Iterable<Object> sortTasksByDate(String sessionId) {
        try{
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            Optional<Iterable<Object>> studentTasks =  studentTaskRepository.sortTasksByDate(new User(userId));
            return studentTasks.orElse(Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Boolean markAsDone(String sessionId, long taskId) {
        try {
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            studentTaskRepository.markAsDone(userId, taskId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeTask(String sessionId, long taskId) {
        try {
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            studentTaskRepository.removeTask(userId, taskId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
