package csed.swe.studentunity.logic;

import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final UserService userService;
    private final UnverifiedUserService unverifiedUserService;
    private final VerificationCodeService verificationCodeService;

    public RegistrationResponses addUser(RegistrationRequest request) {
        if(userService.getUser(request.getEmail()).isPresent()) return RegistrationResponses.USER_ALREADY_EXISTS;
        if(unverifiedUserService.getUnverifiedUser(request.getEmail()).isPresent()) {
            verificationCodeService.sendVerificationCode(request.getEmail());
            return RegistrationResponses.USER_NEEDS_VERIFICATION;
        }
        UnverifiedUser newUser = new UnverifiedUser(request.getEmail(), request.getStudentId(), request.getFirstName(),
                request.getLastName(), request.getPassword(), "student");
        unverifiedUserService.addUnverifiedUser(newUser);
        verificationCodeService.addVerificationCode(newUser.getEmail());

        return RegistrationResponses.SUCCESSFUL_REGISTRATION;
    }

    public RegistrationResponses verifyUser(VerificationRequest request) {

        Optional<VerificationCode> verificationCode = verificationCodeService.getVerificationCode(request.getEmail());
        if(verificationCode.isEmpty() ||
                !verificationCode.get().getCode().equals(request.getCode()) ) return RegistrationResponses.FAILED_VERIFICATION;

        Optional<UnverifiedUser> unverifiedUser = unverifiedUserService.getUnverifiedUser(request.getEmail());
        if(unverifiedUser.isEmpty()) return RegistrationResponses.FAILED_VERIFICATION;

        userService.addUser(convertUnverifiedUserToUser(unverifiedUser.get()));
        unverifiedUserService.deleteUnverifiedUser(unverifiedUser.get().getId());
        verificationCodeService.deleteVerificationCode(unverifiedUser.get().getEmail());
        return RegistrationResponses.SUCCESSFUL_VERIFICATION;
    }


    private User convertUnverifiedUserToUser(UnverifiedUser unverifiedUser) {
        return new User(unverifiedUser.getEmail(), unverifiedUser.getStudentId(), unverifiedUser.getFirstName(),
                unverifiedUser.getLastName(), unverifiedUser.getPassword(), unverifiedUser.getRole());
    }



}
