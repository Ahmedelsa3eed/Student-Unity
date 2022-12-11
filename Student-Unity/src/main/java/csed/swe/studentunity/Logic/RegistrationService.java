package csed.swe.studentunity.Logic;

import csed.swe.studentunity.model.RegistrationRequest;
import csed.swe.studentunity.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
//@Transactional
public class RegistrationService {

    private final UserService userService;

    public String addUser(RegistrationRequest request) {
        // check if email is already in use, if not: add user to database and send verification email
        if(userService.getUser(request.getEmail()).isEmpty()) {
            System.out.println(request);
            User newUser = new User(request.getEmail(), request.getStudentId(), request.getFirstName(),
                    request.getLastName(), request.getPassword(), "student", "");
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
