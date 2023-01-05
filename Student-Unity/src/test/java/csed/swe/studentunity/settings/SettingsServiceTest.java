package csed.swe.studentunity.settings;

import csed.swe.studentunity.dao.UserRepo;
import csed.swe.studentunity.logic.SettingsService;
import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.model.settings.ChangePasswordRequest;
import csed.swe.studentunity.model.settings.ChangeUserNameRequest;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SettingsServiceTest {

    @Autowired
    private SettingsService underTest;

    @Autowired
    private UserService userService;



    private final User user = new User("testsettings@gmail.com", 36987412, "ahmed", "elsaid", "12345", "student");



    @Test
    void itShouldChangePassword() {
        // given
        userService.addUser(user);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(user.getEmail(), "admin", userService.getUser(user.getEmail()).orElseThrow().getId());

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("12345", "55555", "55555");
        // when
        underTest.changePassword(changePasswordRequest, ActiveUserService.getInstance().getSessionIdAsString(user.getEmail()));

        // then
        assertThat(userService.getUser(user.getEmail()).orElseThrow().getPassword()).isEqualTo("55555");

        // clean up
        userService.deleteUser(userService.getUser(user.getEmail()).orElseThrow().getId());
        // Convert session id string to UUID
        UUID sessionId = UUID.fromString(ActiveUserService.getInstance().getSessionIdAsString(user.getEmail()));
        activeUserService.logout(sessionId);
    }

    @Test
    void itShouldChangeName() {
        // given
        userService.addUser(user);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(user.getEmail(), "admin", userService.getUser(user.getEmail()).orElseThrow().getId());

        String firstName = "ahmed";
        String lastName = "elsaid";
        ChangeUserNameRequest changeUserNameRequest = new ChangeUserNameRequest(firstName, lastName);
        // when
        underTest.changeName(changeUserNameRequest, ActiveUserService.getInstance().getSessionIdAsString(user.getEmail()));

        // then
        assertThat(userService.getUser(user.getEmail()).orElseThrow().getFirstName()).isEqualTo("ahmed");
        assertThat(userService.getUser(user.getEmail()).orElseThrow().getLastName()).isEqualTo("elsaid");

        // clean up
        userService.deleteUser(userService.getUser(user.getEmail()).orElseThrow().getId());
        // Convert session id string to UUID
        UUID sessionId = UUID.fromString(ActiveUserService.getInstance().getSessionIdAsString(user.getEmail()));
        activeUserService.logout(sessionId);
    }

    @Test
    void itShouldChangeStudentId() {
        // given
        userService.addUser(user);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(user.getEmail(), "admin", userService.getUser(user.getEmail()).orElseThrow().getId());

        int studentId = 12345678;

        // when
        underTest.changeId(studentId, ActiveUserService.getInstance().getSessionIdAsString(user.getEmail()));

        // then
        assertThat(userService.getUser(user.getEmail()).orElseThrow().getStudentId()).isEqualTo(12345678);

        // clean up
        userService.deleteUser(userService.getUser(user.getEmail()).orElseThrow().getId());
        // Convert session id string to UUID
        UUID sessionId = UUID.fromString(ActiveUserService.getInstance().getSessionIdAsString(user.getEmail()));
        activeUserService.logout(sessionId);

    }




}
