package csed.swe.studentunity.Logic.Tasks;

import csed.swe.studentunity.DAO.StudentTaskRepository;
import csed.swe.studentunity.model.StudentTask;
import csed.swe.studentunity.model.StudentTaskId;
import csed.swe.studentunity.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTaskService {

    private final StudentTaskRepository studentTaskRepository;

    @Autowired
    public StudentTaskService(StudentTaskRepository studentTaskRepository) {
        this.studentTaskRepository = studentTaskRepository;
    }

    public StudentTask saveStudentTask(StudentTask studentTask) {
        return studentTaskRepository.save(studentTask);
    }

    /**
     * blocked by subscribedCoursesService
     * */
    public void addTaskIdToAllSubscribedUsers(Task task) {
//        List<String> emails =  subscribedCoursesService.getAllSubscribesUsers(task.courseCode);
//        for (String email: emails)
//            saveStudentTask(new StudentTask(new StudentTaskId(email, task.getId()), false));
        System.out.println("task is added to subscribed users");
    }
}
