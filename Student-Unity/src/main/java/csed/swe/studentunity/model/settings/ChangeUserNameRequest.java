package csed.swe.studentunity.model.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ChangeUserNameRequest {
    private String firstName;
    private String lastName;

    public String toString() {
        return "LName: " + lastName + " Fname: " + firstName;
    }
}
