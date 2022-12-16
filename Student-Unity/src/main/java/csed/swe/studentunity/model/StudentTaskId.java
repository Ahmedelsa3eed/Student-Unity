package csed.swe.studentunity.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StudentTaskId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id")
    private User studentId;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task taskId;

    public StudentTaskId() {}

    public StudentTaskId(User studentId, Task taskId) {
        this.studentId = studentId;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTaskId that = (StudentTaskId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, taskId);
    }


}
