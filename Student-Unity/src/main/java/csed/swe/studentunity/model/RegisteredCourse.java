package csed.swe.studentunity.model;

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
    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    @Id
    @JoinColumn(name = "id")
    @ManyToOne
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
