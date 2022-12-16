package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
    long id;

    @Column(name = "course_name", nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String name;

    @Column(name = "course_code", nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String code;

    @Column(name = "time_table", nullable = false, columnDefinition = "TEXT")
    private String timeTable;

    @Column(name = "course_status", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean status;

    @Column(name = "telegram_link")
    private String telegramLink;

    @Column(name = "notifications_token")
    private String notificationsToken;

    public Course(String courseCode) {
        this.code = courseCode;
    }

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {

    }

}
