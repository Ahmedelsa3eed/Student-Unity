package csed.swe.studentunity.Logic;

import csed.swe.studentunity.model.RegistrationRequest;
import csed.swe.studentunity.model.UnverifiedUser;
import csed.swe.studentunity.model.User;
import csed.swe.studentunity.model.VerificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
//@Transactional
public class RegistrationService {

    private final UserService userService;
    private final UnverifiedUserService unverifiedUserService;
    private final EmailService emailService;

    public String addUser(RegistrationRequest request) {
        // check if email is already in use, if not: add user to database and send verification email
        if(unverifiedUserService.getUnverifiedUser(request.getEmail()).isEmpty()
        && userService.getUser(request.getEmail()).isEmpty()) {
            UnverifiedUser newUser = new UnverifiedUser(request.getEmail(), request.getStudentId(), request.getFirstName(),
                    request.getLastName(), request.getPassword(), "student");
            String code = unverifiedUserService.addUnverifiedUser(newUser);
            emailService.send("You have registered in Student Unity \n" +
                    "verification code is: " + code, "verification code", request.getEmail());
            return "User registered successfully";
        } else {
            return "User is already exist";
        }
    }

    public String verifyUser(VerificationRequest request) {
        // milestone 2
        boolean response = unverifiedUserService.verifyUser(request.getEmail(), request.getCode());
        if(response) {
            Optional<UnverifiedUser> unverifiedUser = unverifiedUserService.getUnverifiedUser(request.getEmail());
            if(unverifiedUser.isEmpty()) {
                return "User doesn't exist";
            }
            User newUser = convertUnverifiedUserToUser(unverifiedUser.get());
            userService.addUser(newUser);
            unverifiedUserService.deleteUnverifiedUser(unverifiedUser.get().getId());
            return "User verified successfully";
        } else {
            return "User verification failed";
        }

    }

    private User convertUnverifiedUserToUser(UnverifiedUser unverifiedUser) {
        return new User(unverifiedUser.getEmail(), unverifiedUser.getStudentId(), unverifiedUser.getFirstName(),
                unverifiedUser.getLastName(), unverifiedUser.getPassword(), unverifiedUser.getRole());
    }



}
