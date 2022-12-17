package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentTaskRepository extends JpaRepository<StudentTask, StudentTaskId> {

    @Query(value="SELECT t.task_id,t.course_code,t.title,t.due_date,t.telegram_link,st.status\n" +
            "FROM task AS t, student_task AS st, user AS u \n" +
            "WHERE t.task_id = st.task_id AND u.id = :userId AND st.id = :userId",
            nativeQuery = true
    )
    Optional<Iterable<Object>> findAllStudentTasks(@Param("userId") long userId);

    @Query("SELECT t.title, t.dueDate,t.telegramLink, st.status " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            "WHERE t = st.studentTaskId.task  AND u.id = :userId " +
            "AND st.studentTaskId.user.id = :userId AND t.course.code = :courseCode"
    )
    Optional<Iterable<Object>> filterStudentTasksByCourse(@Param("userId") Long userId,
                                                          @Param("courseCode") String courseCode);

    @Query("SELECT t.title, t.dueDate,t.telegramLink , st.status  " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            "WHERE t = st.studentTaskId.task  AND u.id = :userId " +
            "AND st.studentTaskId.user.id = :userId ORDER BY t.dueDate"
    )
    Optional<Iterable<Object>> sortTasksByDate(@Param("user") User userId);

    @Modifying
    @Query("update StudentTask st set st.status = :newStatus " +
            "where st.studentTaskId.user.id = :userId and st.studentTaskId.task.taskId = :taskId"
    )
    void markAsDone(@Param("userId") Long userId, @Param("taskId") Long taskId, @Param("newStatus") boolean newStatus);

    @Modifying
    @Query("delete from StudentTask st " +
            "where st.studentTaskId.user.id = :userId and st.studentTaskId.task.taskId = :taskId"
    )
    void removeTask(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Modifying
    @Query(value =
            "INSERT INTO studentTask AS st(u.id, :task)  " +
            "SELECT u.id " +
            "FROM user AS u JOIN registered_course AS rc ON u.id = rc.user.id" +
            "WHERE rc.course.course_code = :courseCode", nativeQuery = true
    )
    void addTaskIdToAllSubscribedUsers(@Param("task") Long taskId, @Param("courseCode") String courseCode);
}
