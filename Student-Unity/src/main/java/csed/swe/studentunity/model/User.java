package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity(name = "User")
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email")
        })
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)

    private Long id;

    @Column(name = "email", nullable = false, updatable = false, columnDefinition = "varchar(100)")
    private String email;

    @Column(name = "student_id", nullable = false, updatable = false)
    private Integer studentId;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RevisionNotification revisionNotification;

    public User() {}

    public User(String email, Integer studentId, String firstName, String lastName, String password, String role) {
        this.email = email;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }
    public User(String email, Integer studentId, String firstName, String lastName, String role) {
        this.email = email;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(Long userId) {
        this.id = userId;
    }
}
