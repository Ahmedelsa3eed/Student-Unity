package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentTaskRepo extends JpaRepository<StudentTask, StudentTaskId> {

    @Query(value="SELECT t.task_id,c.course_code,t.title,t.due_date,t.telegram_link,st.status " +
            "FROM task as t NATURAL JOIN student_task as st NATURAL JOIN user as u NATURAL JOIN course as c " +
            "WHERE st.id = :userId",
            nativeQuery = true
    )
    Optional<Iterable<Object>> findAllStudentTasks(@Param("userId") long userId);

    @Query("SELECT t.taskId, c.code , t.title ,t.dueDate,t.telegramLink, st.status " +
            "FROM Task AS t, StudentTask AS st, User AS u , Course as c " +
            "WHERE t.taskId = st.studentTaskId.task.taskId  AND u.id = :userId AND c.id = t.course.id " +
            "AND st.studentTaskId.user.id = :userId " +
            "AND t.course.id = :courseId " +
            "AND st.status = :status "
    )
    Optional<Iterable<Object>> filterStudentTasksByCourse(@Param("userId") Long userId,
                                                          @Param("courseId") Long courseId,
                                                          @Param("status") Boolean status);

    @Query("SELECT t.title, t.dueDate,t.telegramLink , st.status  " +
            "FROM Task AS t, StudentTask AS st, User AS u " +
            "WHERE t = st.studentTaskId.task  AND u.id = :userId " +
            "AND st.studentTaskId.user.id = :userId ORDER BY t.dueDate"
    )
    Optional<Iterable<Object>> sortTasksByDate(@Param("userId") Long userId);

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
            "INSERT INTO student_task (id, task_id) " +
            "SELECT u.id, :taskId " +
            "FROM user AS u JOIN registered_course AS rc ON u.id = rc.id " +
            "WHERE rc.course_id = :courseId", nativeQuery = true
    )
    void addTaskIdToAllSubscribedUsers(@Param("taskId") Long taskId, @Param("courseId") Long courseId);
}
