package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.TaskService;
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
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/editTask")
    public ResponseEntity<Task> editTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.editTask(task), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestParam("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task was deleted successfully!", HttpStatus.OK);
    }

}
