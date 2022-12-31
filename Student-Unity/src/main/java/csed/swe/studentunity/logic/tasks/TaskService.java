package csed.swe.studentunity.logic.tasks;

import csed.swe.studentunity.dao.TaskRepo;
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
    public Task editTask(Task task) {
        // get the task from the database
        if (task == null) return null;
        Task taskFromDB = this.taskRepo.findById(task.getTaskId()).orElse(null);
        System.out.println("Task from db course id is "+ taskFromDB.getCourse().getId());
        if (taskFromDB == null) return null;
        // the task updated field is not the course id
        if ( task.getCourse().getId() == taskFromDB.getCourse().getId() ){
            this.taskRepo.save(task);
        }else{
            // the task updated field is the course id
            // delete the task from the old course
            this.taskRepo.delete(taskFromDB);
            // add the task to the new course
            this.addTask(task);
        }

        return task;
    }

    public Boolean deleteTaskById(Long taskId) {
        if (taskId == null) return false;
        this.taskRepo.deleteById(taskId);
        return true;
    }

}
