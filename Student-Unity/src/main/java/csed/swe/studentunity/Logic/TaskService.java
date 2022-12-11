package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.TaskRepository;
import csed.swe.studentunity.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * @return the saved entity, including the updated id field
     * */
    public Task addTask(Task task) {
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
