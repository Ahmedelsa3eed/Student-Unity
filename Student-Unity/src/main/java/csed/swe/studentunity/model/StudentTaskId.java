package csed.swe.studentunity.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StudentTaskId implements Serializable {

    private String email;
    private Long taskId;

    public StudentTaskId() {}

    public StudentTaskId(String email, Long taskId) {
        this.email = email;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTaskId other = (StudentTaskId) o;
        return email.equals(other.email) && taskId.equals(other.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, taskId);
    }

}
