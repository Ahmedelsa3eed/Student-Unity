package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
@Entity(name = "VerificationCode")
@Table(name = "verification_code",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email"),
        })
public class VerificationCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email", nullable = false, updatable = false, columnDefinition = "varchar(100)")
    private String email;

    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    private String code;

    public VerificationCode() {}

    public VerificationCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

}