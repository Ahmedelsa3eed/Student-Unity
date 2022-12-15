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
    private Long courseId;

    @Id
    private Long userId;

    @Column(name = "revision_subscription")
    private Boolean revisionSubscription;

}
