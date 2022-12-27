package csed.swe.studentunity.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "StudentTask")
@Table(name = "student_task")
public class StudentTask implements Serializable {

    @EmbeddedId
    private StudentTaskId studentTaskId;

    @ColumnDefault("false")
    @Column(name = "status", nullable = false)
    private boolean status = false;

    public StudentTask() {}

    public StudentTask(StudentTaskId studentTaskId, boolean status) {
        this.studentTaskId = studentTaskId;
        this.status = status;
    }

    public StudentTask(boolean status) {
        this.status = status;
    }

}
