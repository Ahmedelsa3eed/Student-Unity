package csed.swe.studentunity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "Course")
@Table(name = "course",
        uniqueConstraints = {
                @UniqueConstraint(name = "code_unique", columnNames = "course_code"),
        })
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "course_id")
    long id;

    @Column(name = "course_name", nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String name;

    @Column(name = "course_code", nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String code;

    @Column(name = "term")
    private Integer term;


    @JsonManagedReference
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ActiveCourse activeCourse;

    @OneToMany(mappedBy="course", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RegisteredCourse> registeredCourses = new ArrayList<>();

    public Course() { }

    public Course(String courseCode) {
        this.code = courseCode;
    }

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name, String code, Integer term) {
        this.name = name;
        this.code = code;
        this.term = term;
    }
}
