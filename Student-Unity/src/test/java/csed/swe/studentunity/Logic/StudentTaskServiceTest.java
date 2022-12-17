package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.StudentTaskRepository;
import csed.swe.studentunity.Logic.Tasks.StudentTaskService;
import csed.swe.studentunity.Logic.User.ActiveUserService;
import csed.swe.studentunity.model.StudentTask;
import csed.swe.studentunity.model.StudentTaskId;
import csed.swe.studentunity.model.Task;
import csed.swe.studentunity.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class StudentTaskServiceTest {

    @MockBean
    private StudentTaskRepository studentTaskRepository;
    @MockBean
    private ActiveUserService activeUserService;
    @InjectMocks
    private StudentTaskService studentTaskService;

    @BeforeEach
    public void setUp() {
        activeUserService = Mockito.mock(ActiveUserService.class);
        setMock(activeUserService);
    }

    private void setMock(ActiveUserService mock) {
        try {
            Field instance = ActiveUserService.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    Iterable<Object> studentTasks =  List.of(
            new StudentTask(new StudentTaskId(new User(1L), new Task(11L)), false),
            new StudentTask(new StudentTaskId(new User(2L), new Task(12L)), false),
            new StudentTask(new StudentTaskId(new User(3L), new Task(13L)), false)
    );
    @Test
    void getTasksCaseUserNotLoggedIn() {
        String sessionId = null;
        Mockito.when(studentTaskRepository.findAllStudentTasks(12345L)).thenReturn(null);
        Iterable<Object> result =studentTaskService.getTasks(sessionId);
        assertSame(Collections.emptyList(),result);
    }

    @Test
    void getTasksCaseUserLoggedIn() {
        String sessionId = "a1633f4e-2994-4eee-bd4e-235a714adb18";
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId))).thenReturn(1L);
        Mockito.when(studentTaskRepository.findAllStudentTasks(1L)).thenReturn(Optional.ofNullable(studentTasks));
        Iterable<Object> result =studentTaskService.getTasks(sessionId);
        assertSame(studentTasks,result);
    }

    @Test
    void getTasksCaseUserLoggedInButNoTasks() {
        String sessionId = "a1633f4e-2994-4eee-bd4e-235a714adb18";
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId))).thenReturn(1L);
        Mockito.when(studentTaskRepository.findAllStudentTasks(12345L)).thenReturn(Optional.empty());
        Iterable<Object> result = studentTaskService.getTasks(sessionId);
        assertSame(Collections.emptyList(), result);
    }

    @Test
    void filterTasksByCourseHavingNoTaskCourse(){
        String sessionId = "a1633f4e-2994-4eee-bd4e-235a714adb18";
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId))).thenReturn(1L);
        Mockito.when(studentTaskRepository.filterStudentTasksByCourse(1L,"ABC")).thenReturn(Optional.empty());
        Iterable<Object> result =studentTaskService.filterTasksByCourse(sessionId,"ABC");
        assertSame(Collections.emptyList(), result);

    }

    @Test
    void filterTasksByCourseHavingTasks(){
        String sessionId = "a1633f4e-2994-4eee-bd4e-235a714adb18";
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId))).thenReturn(1L);
        Mockito.when(studentTaskRepository.filterStudentTasksByCourse(1L,"ABC")).thenReturn(Optional.ofNullable(studentTasks));
        Iterable<Object> result =studentTaskService.filterTasksByCourse(sessionId,"ABC");
        assertSame(studentTasks, result);
    }

    @Test
    void filterTasksByCourseHavingNoSessionId(){
        String sessionId = null;
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(studentTaskRepository.filterStudentTasksByCourse(1L,"ABC")).thenReturn(Optional.empty());
        Iterable<Object> result =studentTaskService.filterTasksByCourse(sessionId,"ABC");
        assertSame(Collections.emptyList(), result);
    }

    @Test
    void removeTaskCaseUserNotLoggedIn() {
        String sessionId = null;
        Mockito.when(studentTaskRepository.findAllStudentTasks(12345L)).thenReturn(null);
        assertFalse(       studentTaskService.removeTask(sessionId,11L));
    }

    @Test
    void removeTaskCaseUserLoggedIn() {
        String sessionId = "a1633f4e-2994-4eee-bd4e-235a714adb18";
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId))).thenReturn(1L);
        assertTrue( studentTaskService.removeTask(sessionId, 11L));
    }


    @Test
    void markAsDoneCaseUserNotLoggedIn() {
        String sessionId = null;
        Mockito.when(studentTaskRepository.findAllStudentTasks(12345L)).thenReturn(null);
        assertFalse(studentTaskService.markAsDone(sessionId,11L, true));
    }

    @Test
    void markAsDone(){
        String sessionId = "a1633f4e-2994-4eee-bd4e-235a714adb18";
        studentTaskService = new StudentTaskService(studentTaskRepository);
        Mockito.when(activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId))).thenReturn(1L);
        assertTrue(studentTaskService.markAsDone(sessionId,11L, false));


    }



}