package csed.swe.studentunity.Logic.Tasks;

import csed.swe.studentunity.DAO.StudentTaskRepo;
import csed.swe.studentunity.DAO.TaskRepo;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @MockBean
    private StudentTaskRepo studentTaskRepo;

    @MockBean
    private StudentTaskService studentTaskService;

    @MockBean
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        this.studentTaskService = new StudentTaskService(this.studentTaskRepo);
        this.taskService = new TaskService(this.taskRepo, this.studentTaskService);
    }

//    @Test
//    void itShouldAddTask() {
//        Task mockTask = Mockito.mock(Task.class);
//        Course mockCourse = Mockito.mock(Course.class);
//        Mockito.when(mockCourse.getCode()).thenReturn("code");
//        Mockito.when(mockTask.getTaskId()).thenReturn(11L);
//        Mockito.when(mockTask.getCourse()).thenReturn(mockCourse);
//        Mockito.doNothing().when(studentTaskRepo).findAllStudentTasks(mockTask.getTaskId());
//
//        Mockito.when(this.studentTaskService.addTaskIdToAllSubscribedUsers(mockTask)).thenReturn(true);
//        assertTrue(this.taskService.addTask(mockTask));
//    }

    @Test
    void itShouldNotAddTaskWhenNullTask() {
        assertFalse(this.taskService.addTask(null));
    }

    @Test
    void itShouldNotAddTaskWhenTaskHasNullCourse() {
        Task mockTask = Mockito.mock(Task.class);
        Mockito.when(mockTask.getCourse()).thenReturn(null);
        Mockito.when(this.taskRepo.save(mockTask)).thenReturn(mockTask);

        assertFalse(this.taskService.addTask(mockTask));
    }

    @Test
    void itShouldNotAddTaskWhenTaskHasNullCourseCode() {
        Task mockTask = Mockito.mock(Task.class);
        Course mockCourse = new Course(null);
        Mockito.when(mockTask.getCourse()).thenReturn(mockCourse);
        Mockito.when(mockTask.getCourse().getCode()).thenReturn(null);

        assertFalse(this.taskService.addTask(mockTask));
    }

    @Test
    void itShouldEditTask() {
        Task mockTask = Mockito.mock(Task.class);

        assertTrue(this.taskService.editTask(mockTask));
    }

    @Test
    void itShouldNotEditNullTask() {
        assertFalse(this.taskService.editTask(null));
    }

    @Test
    void itShouldDeleteTaskById() {
        assertTrue(this.taskService.deleteTaskById(11L));
    }

    @Test
    void itShouldNotDeleteNullTaskId() {
        assertFalse(this.taskService.deleteTaskById(null));
    }
}