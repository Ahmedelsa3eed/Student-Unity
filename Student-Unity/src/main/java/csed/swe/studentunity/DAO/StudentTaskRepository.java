package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentTaskRepository extends JpaRepository<StudentTask, StudentTaskId> {

    @Query("SELECT new Task (t.title, t.dueDate, t.telegramLink), new StudentTask(st.status) " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            "WHERE t = st.studentTaskId.task AND u.id = :userId AND st.studentTaskId.user.id = :userId"
    )
    Optional<Iterable<Object>> findAllStudentTasks(@Param("userId") long userId);

    @Query("SELECT new Task (t.title, t.dueDate,t.telegramLink), new StudentTask(st.status)  " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            "WHERE t = st.studentTaskId.task  AND u.id = :userId " +
            "AND st.studentTaskId.user.id = :userId AND t.course.code = :courseCode"
    )
    Optional<Iterable<Object>> filterStudentTasksByCourse(@Param("userId") Long userId,
                                                          @Param("courseCode") String courseCode);

    @Query("SELECT new Task (t.title, t.dueDate,t.telegramLink), new StudentTask(st.status)  " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            "WHERE t = st.studentTaskId.task  AND u.id = :userId " +
            "AND st.studentTaskId.user.id = :userId ORDER BY t.dueDate"
    )
    Optional<Iterable<Object>> sortTasksByDate(@Param("userId") Long userId);

    @Modifying
    @Query("update StudentTask st set st.status = true " +
            "where st.studentTaskId.user.id = :userId and st.studentTaskId.task.taskId = :taskId"
    )
    void markAsDone(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Modifying
    @Query("delete from StudentTask st " +
            "where st.studentTaskId.user.id = :userId and st.studentTaskId.task.taskId = :taskId"
    )
    void removeTask(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Modifying
    @Query(value =
            "INSERT INTO student_task st (u.id, :taskId) " +
            "SELECT u.id " +
            "FROM user AS u JOIN registered_course AS rc ON u.id = rc.user.id" +
            "WHERE rc.course.course_code = :courseCode", nativeQuery = true
    )
    void addTaskIdToAllSubscribedUsers(@Param("taskId") Long taskId, @Param("courseCode") String courseCode);
}
