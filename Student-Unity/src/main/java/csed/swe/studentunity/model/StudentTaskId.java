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
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public StudentTaskId() {}

    public StudentTaskId(User user, Task task) {
        this.user = user;
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTaskId that = (StudentTaskId) o;
        return Objects.equals(user, that.user) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, task);
    }

}
