package csed.swe.studentunity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@IdClass(RegisteredCourseId.class)
@Table(name = "registered_course")
public class RegisteredCourse implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonManagedReference
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonManagedReference
    private User user;

    @Column(name = "revision_subscription")
    private Boolean revisionSubscription;

    public RegisteredCourse() {}

    public RegisteredCourse(Course course, User user, Boolean revisionSubscription) {
        this.course = course;
        this.user = user;
        this.revisionSubscription = revisionSubscription;
    }

}
