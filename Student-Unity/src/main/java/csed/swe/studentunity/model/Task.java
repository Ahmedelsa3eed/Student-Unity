package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Entity
public class Task implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "taskId")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;
    private String title;

    public Task() {}
}
