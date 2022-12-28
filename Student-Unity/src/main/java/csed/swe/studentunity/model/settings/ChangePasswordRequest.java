package csed.swe.studentunity.model.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public String toString() {
        return "currentPassword: " + currentPassword + " newPassword: " + newPassword + " confirmPassword: " + confirmPassword;
    }

}

