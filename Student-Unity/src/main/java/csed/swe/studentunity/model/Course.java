package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "Course")
@Table(name = "course", uniqueConstraints = {
        @UniqueConstraint(name = "course_code", columnNames = "course_code"),
    })
public class Course {
    @Column(name = "course_name", nullable = false, columnDefinition = "TEXT")
    String courseName;

    @Id
    @Column(name = "course_code", nullable = false, columnDefinition = "TEXT")
    String courseCode;

    @Column(name = "timeTable", nullable = false, columnDefinition = "TEXT")
    String timeTable;

    @Column(name = "telegramLink", nullable = false, columnDefinition = "TEXT")
    String telegramLink;

    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN")
    boolean status;

    @Column(name = "notificationsToken", nullable = false, columnDefinition = "TEXT")
    String notificationsToken;

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }
}
