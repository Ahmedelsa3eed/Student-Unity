package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Entity
public class Course implements Serializable {
    @Id @Column(nullable = false, columnDefinition = "varchar(10)")
    private String courseCode;
    private String courseName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @JoinColumn(name="taskId")
    private List<Task> tasks;

    public Course() {}

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

}
