package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.TaskRepository;
import csed.swe.studentunity.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final StudentTaskService studentTaskService;

    @Autowired
    public TaskService(TaskRepository taskRepository, StudentTaskService studentTaskService) {
        this.taskRepository = taskRepository;
        this.studentTaskService = studentTaskService;
    }

    /**
     * @return the saved entity, including the updated id field
     * */
    public Task addTask(Task task) {
        studentTaskService.addTaskIdToAllSubscribedUsers(task);
        return this.taskRepository.save(task);
    }

    /**
     * @return the saved entity
     * */
    public Task editTask(Task task) {
        return this.taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        this.taskRepository.deleteById(taskId);
    }


}
