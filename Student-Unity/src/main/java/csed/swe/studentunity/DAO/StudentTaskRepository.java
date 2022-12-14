package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentTaskRepository extends JpaRepository<StudentTask, StudentTaskId> {

    @Query("SELECT t.title ,st.status, u.firstName, u.lastName, u.email,t.dueDate " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            " WHERE t = st.studentTaskId.taskId  AND u = :user AND st.studentTaskId.studentId = :user"
    )
    Iterable<Object> findAllStudentTasksByEmail(@Param("user") User user);

    @Query("SELECT t.title ,st.status, u.firstName, u.lastName, u.email,t.dueDate " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            " WHERE t = st.studentTaskId.taskId  AND u = :user " +
            "AND st.studentTaskId.studentId = :user AND t.courseCode = :courseCode")
    Iterable<Object> findAllStudentTasksByEmailAndCourseCode(@Param("user") User user, @Param("courseCode") Course courseCode);

    @Query("SELECT t.title ,st.status, u.firstName, u.lastName, u.email, t.dueDate " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            " WHERE t = st.studentTaskId.taskId  AND u = :user " +
            "AND st.studentTaskId.studentId = :user ORDER BY t.dueDate")
    Iterable<Object> sortTasksByDate(@Param("user") User user);

    @Modifying
    @Query("update StudentTask st set st.status = true where st.studentTaskId.studentId = :user and st.studentTaskId.taskId = :task")
    void markAsDone(@Param("user") User user, @Param("task") Task task);

    @Modifying
    @Query("delete from StudentTask st where st.studentTaskId.studentId = :user and st.studentTaskId.taskId = :task")
    void removeTask(@Param("user") User user, @Param("task") Task task);

}
