package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface StudentTaskRepository extends JpaRepository<StudentTask, StudentTaskId> {

    @Query("SELECT new Task (t.title, t.dueDate,t.telegramLink), new StudentTask(st.status) " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            " WHERE t = st.studentTaskId.taskId  AND u.id = :userId AND st.studentTaskId.studentId.id = :userId"
    )
    Optional<Iterable<Object>> findAllStudentTasks(@Param("userId") long userId);

    @Query("SELECT new Task (t.title, t.dueDate,t.telegramLink), new StudentTask(st.status)  " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            " WHERE t = st.studentTaskId.taskId  AND u.id = :userId " +
            "AND st.studentTaskId.studentId.id = :userId AND t.courseCode.courseCode = :courseCode")
    Optional<Iterable<Object>> filterStudentTasksByCourse(@Param("userId") Long userId, @Param("courseCode") String courseCode);

    @Query("SELECT new Task (t.title, t.dueDate,t.telegramLink), new StudentTask(st.status)  " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            " WHERE t = st.studentTaskId.taskId  AND u.id = :userId " +
            "AND st.studentTaskId.studentId.id = :userId ORDER BY t.dueDate")
    Optional<Iterable<Object>> sortTasksByDate(@Param("userId") User userId);

    @Modifying
    @Query("update StudentTask st set st.status = true where st.studentTaskId.studentId.id = :userId and st.studentTaskId.taskId.taskId = :taskId")
    void markAsDone(@Param("userId") Long userId, @Param("taskId") Long task);

    @Modifying
    @Query("delete from StudentTask st where st.studentTaskId.studentId.id = :userId and st.studentTaskId.taskId.taskId = :taskId")
    void removeTask(@Param("userId") Long userId, @Param("taskId") Long taskId);


}
