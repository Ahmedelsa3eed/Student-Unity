package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity(name = "UnverifiedUser")
@Table(name = "unverified_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email"),
                @UniqueConstraint(name = "student_id_unique", columnNames = "student_id")
        })
public class UnverifiedUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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



    public UnverifiedUser() {}


    public UnverifiedUser(String email, Integer studentId, String firstName, String lastName, String password, String student) {
    }
}
