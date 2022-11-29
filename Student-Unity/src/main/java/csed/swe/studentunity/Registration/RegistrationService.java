package csed.swe.studentunity.Registration;

import csed.swe.studentunity.user.User;
import csed.swe.studentunity.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
//@Transactional
public class RegistrationService {
    private final UserService userService;
    public String addUser(RegistrationRequest request) {
        // check if email is already in use
        // if not, add user to database
        // send verification email
        if(userService.getUser(request.getEmail()).isEmpty()) {
            User newUser = new User(request.getEmail(), request.getId(), request.getFirstName(),
                    request.getLastName(), request.getPassword(), "normal", "");
            userService.addUser(newUser);
            return "User registered successfully";
        } else {
            return "User is already exist";
        }
    }

    public String verifyUser() {
        // milestone 2
        return "works";
    }

    public String generateCode() {
        // milestone 2
        return "works";
    }

}
