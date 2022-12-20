package csed.swe.studentunity.Logic.Tasks;

import csed.swe.studentunity.DAO.TaskRepo;
import csed.swe.studentunity.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {

    private final TaskRepo taskRepo;
    private final StudentTaskService studentTaskService;

    @Autowired
    public TaskService(TaskRepo taskRepo, StudentTaskService studentTaskService) {
        this.taskRepo = taskRepo;
        this.studentTaskService = studentTaskService;
    }

    /**
     * @return the saved entity, including the updated id field
     * */
    public Boolean addTask(Task task) {
        if (task == null) return false;
        task = this.taskRepo.save(task);
        return studentTaskService.addTaskIdToAllSubscribedUsers(task);
    }

    /**
     * @return the saved entity
     * */
    public Boolean editTask(Task task) {
        if (task == null) return false;
        this.taskRepo.save(task);
        return true;
    }

    public Boolean deleteTaskById(Long taskId) {
        if (taskId == null) return false;
        this.taskRepo.deleteById(taskId);
        return true;
    }

}
