package csed.swe.studentunity.Registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer id;
}
