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
        @UniqueConstraint(name = "code", columnNames = "code"),
    })
public class Course {

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    String name;

    @Id
    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    String code;

    @Column(name = "timeTable", nullable = false, columnDefinition = "TEXT")
    String timeTable;

    @Column(name = "telegramLink", nullable = false, columnDefinition = "TEXT")
    String telegramLink;

    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN")
    boolean status;

    @Column(name = "notificationsToken", nullable = false, columnDefinition = "TEXT")
    String notificationsToken;

}
