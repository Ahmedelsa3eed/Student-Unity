package csed.swe.studentunity.SigningDatabaseManagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "User")
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email"),
                @UniqueConstraint(name = "student_id_unique", columnNames = "student_id")
        }
)
public class User implements Serializable {

    @Id
    @Column(
            name = "email",
            nullable = false,
            updatable = false
    )
    private String email;

    @Column(
            name = "student_id",
            nullable = false,
            updatable = false
    )
    private Integer studentId;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "role",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String role;

    @Column(
            name = "revision_notification_token"
    )
    private String revisionNotificationToke;

    public User(){}

}
