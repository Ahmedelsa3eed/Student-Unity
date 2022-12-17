package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo extends JpaRepository<Task, Long> {

}


