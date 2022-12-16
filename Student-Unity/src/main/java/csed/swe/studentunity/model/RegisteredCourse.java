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
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "revision_subscription")
    private Boolean revisionSubscription;

    public RegisteredCourse() {}

    public RegisteredCourse(Long courseId, Long userId, Boolean revisionSubscription) {
        this.courseId = courseId;
        this.userId = userId;
        this.revisionSubscription = revisionSubscription;
    }

}
