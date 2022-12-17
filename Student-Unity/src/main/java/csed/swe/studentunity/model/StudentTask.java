package csed.swe.studentunity.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "StudentTask")
public class StudentTask implements Serializable {

    @EmbeddedId
    private StudentTaskId studentTaskId;

    @Column(name = "status")
    private boolean status;

    public StudentTask() {}

    public StudentTask(StudentTaskId studentTaskId, boolean status) {
        this.studentTaskId = studentTaskId;
        this.status = status;
    }

    public StudentTask(boolean status) {
        this.status = status;
    }

}
