package csed.swe.studentunity.logic.tasks;

import csed.swe.studentunity.dao.StudentTaskRepo;
import csed.swe.studentunity.logic.user.ActiveUserService;
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

    private final StudentTaskRepo studentTaskRepo;
    @Autowired
    public StudentTaskService(StudentTaskRepo studentTaskRepo) {
        this.studentTaskRepo = studentTaskRepo;
    }

    public StudentTask saveStudentTask(StudentTask studentTask) {
        return studentTaskRepo.save(studentTask);
    }

    public Boolean addTaskIdToAllSubscribedUsers(Task task) {
        if (task == null || task.getTaskId() == null || task.getCourse() == null || task.getCourse().getCode() == null)
            return false;
        this.studentTaskRepo.addTaskIdToAllSubscribedUsers(task.getTaskId(), task.getCourse().getId());
        return true;
    }

    public Iterable<Object> getTasks(String sessionId) {
        try{
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            Optional<Iterable<Object>> studentTasks =  studentTaskRepo.findAllStudentTasks(userId);
            return studentTasks.orElse(Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Iterable<Object> filterTasksByCourse(String sessionId, Long courseId, Boolean status) {
        try{
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            Optional<Iterable<Object>> studentTasks =  studentTaskRepo.filterStudentTasksByCourse(userId, courseId, status);
            return studentTasks.orElse(Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Iterable<Object> sortTasksByDate(String sessionId) {
        try{
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            Optional<Iterable<Object>> studentTasks =  studentTaskRepo.sortTasksByDate(userId);
            return studentTasks.orElse(Collections.emptyList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Boolean markAsDone(String sessionId, long taskId, boolean newStatus) {
        try {
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            studentTaskRepo.markAsDone(userId, taskId, newStatus);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeTask(String sessionId, long taskId) {
        try {
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
            studentTaskRepo.removeTask(userId, taskId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
