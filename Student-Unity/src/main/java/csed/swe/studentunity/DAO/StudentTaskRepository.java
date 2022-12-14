package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.StudentTask;
import csed.swe.studentunity.model.StudentTaskId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTaskRepository extends JpaRepository<StudentTask, StudentTaskId> {
}
