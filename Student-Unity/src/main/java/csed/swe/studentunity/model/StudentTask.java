package csed.swe.studentunity.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Entity
public class StudentTask implements Serializable {

    @EmbeddedId
    private StudentTaskId studentTaskId;

    private boolean status;

    public StudentTask() {}

    public StudentTask(StudentTaskId studentTaskId, boolean status) {
        this.studentTaskId = studentTaskId;
        this.status = status;
    }

}
