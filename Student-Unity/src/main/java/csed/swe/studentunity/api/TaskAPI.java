package csed.swe.studentunity.api;

import csed.swe.studentunity.logic.tasks.TaskService;
import csed.swe.studentunity.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskAPI {

    private final TaskService taskService;

    public TaskAPI(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addTask")
    public ResponseEntity<Boolean> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/editTask")
    public ResponseEntity<Task> editTask(@RequestBody Task task) {
        Task editedTask = taskService.editTask(task);
        if (editedTask == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(editedTask, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<Boolean> deleteTask(@RequestParam("taskId") Long taskId) {
        return new ResponseEntity<>(taskService.deleteTaskById(taskId), HttpStatus.OK);
    }

}
