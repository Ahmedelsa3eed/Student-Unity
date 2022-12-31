package csed.swe.studentunity.registration;

import csed.swe.studentunity.logic.RegistrationService;
import csed.swe.studentunity.logic.UnverifiedUserService;
import csed.swe.studentunity.logic.VerificationCodeService;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class RegistrationServiceTest {
    @Autowired
    private RegistrationService underTest;
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private UnverifiedUserService unverifiedUserService;

    private final RegistrationRequest registrationRequest = new RegistrationRequest(
            "Adel", "Elsaid", "lol@gmail.com", "25420", 20202024
    );

    @Test
    void itShouldAddUser() {
        // when
        RegistrationResponses response = underTest.addUser(registrationRequest);

        // then
        RegistrationResponses expected = RegistrationResponses.SUCCESSFUL_REGISTRATION;
        assertThat(response).isEqualTo(expected);

        // clean up
        unverifiedUserService.deleteUnverifiedUser(unverifiedUserService.getUnverifiedUser(registrationRequest.getEmail()).get().getId());
    }

    @Test
    void itShouldFindUserExisted() {
        // given
        User user = new User(registrationRequest.getEmail(), registrationRequest.getStudentId(), registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getPassword(), "student");

        // when
        userService.addUser(user);
        RegistrationResponses response = underTest.addUser(registrationRequest);

        // then
        RegistrationResponses expected = RegistrationResponses.USER_ALREADY_EXISTS;
        assertThat(response).isEqualTo(expected);

        // clean up
        userService.deleteUser(userService.getUser(registrationRequest.getEmail()).orElseThrow().getId());
    }

    @Test
    void itShouldFindUserNeedsVerification() {
        // given
        UnverifiedUser unverifiedUser = new UnverifiedUser(registrationRequest.getEmail(), registrationRequest.getStudentId(), registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getPassword(), "student");

        // when
        unverifiedUserService.addUnverifiedUser(unverifiedUser);
        RegistrationResponses response = underTest.addUser(registrationRequest);

        // then
        RegistrationResponses expected = RegistrationResponses.USER_NEEDS_VERIFICATION;
        assertThat(response).isEqualTo(expected);

        // clean up
        unverifiedUserService.deleteUnverifiedUser(unverifiedUserService.getUnverifiedUser(registrationRequest.getEmail()).orElseThrow().getId());
    }

    @Test
    void itShouldMakeSuccessfulVerification() {
        // given
        UnverifiedUser unverifiedUser = new UnverifiedUser(registrationRequest.getEmail(), registrationRequest.getStudentId(), registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getPassword(), "student");

        // when
        unverifiedUserService.addUnverifiedUser(unverifiedUser);
        String code = verificationCodeService.addVerificationCode(unverifiedUser.getEmail());
        VerificationRequest verificationRequest = new VerificationRequest(unverifiedUser.getEmail(), code);
        RegistrationResponses response = underTest.verifyUser(verificationRequest);

        // then
        RegistrationResponses expected = RegistrationResponses.SUCCESSFUL_VERIFICATION;
        assertThat(response).isEqualTo(expected);

        // clean up
        userService.deleteUser(userService.getUser(registrationRequest.getEmail()).orElseThrow().getId());
    }

    @Test
    void itShouldMakeFailedVerification() {
        // given
        UnverifiedUser unverifiedUser = new UnverifiedUser(registrationRequest.getEmail(), registrationRequest.getStudentId(), registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getPassword(), "student");

        // when
        unverifiedUserService.addUnverifiedUser(unverifiedUser);
        VerificationRequest verificationRequest = new VerificationRequest(unverifiedUser.getEmail(), "code");
        RegistrationResponses response = underTest.verifyUser(verificationRequest);

        // then
        RegistrationResponses expected = RegistrationResponses.FAILED_VERIFICATION;
        assertThat(response).isEqualTo(expected);

        // clean up
        unverifiedUserService.deleteUnverifiedUser(unverifiedUserService.getUnverifiedUser(registrationRequest.getEmail()).orElseThrow().getId());
        verificationCodeService.deleteVerificationCode(registrationRequest.getEmail());
    }

    @Test
    void itShouldMakeFailedVerification2() {
        // given
        UnverifiedUser unverifiedUser = new UnverifiedUser(registrationRequest.getEmail(), registrationRequest.getStudentId(), registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getPassword(), "student");

        // when
        unverifiedUserService.addUnverifiedUser(unverifiedUser);
        String code = verificationCodeService.addVerificationCode(unverifiedUser.getEmail());
        VerificationRequest verificationRequest = new VerificationRequest(registrationRequest.getEmail(), "wrong code");
        RegistrationResponses response = underTest.verifyUser(verificationRequest);

        // then
        RegistrationResponses expected = RegistrationResponses.FAILED_VERIFICATION;
        assertThat(response).isEqualTo(expected);

        // clean up
        unverifiedUserService.deleteUnverifiedUser(unverifiedUserService.getUnverifiedUser(registrationRequest.getEmail()).orElseThrow().getId());
        verificationCodeService.deleteVerificationCode(registrationRequest.getEmail());

    }
}