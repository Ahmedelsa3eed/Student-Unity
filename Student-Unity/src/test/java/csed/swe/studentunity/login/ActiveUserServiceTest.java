package csed.swe.studentunity.login;

import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.logic.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class ActiveUserServiceTest {

    private ActiveUserService underTest = ActiveUserService.getInstance();
    @Autowired
    private UserService userService;

    @Test
    void itShouldAddSessionSuccessfully() {
        // given
        String email = "test@gmail.com";

        // when
        underTest.login(email, "student", 1L);

        // then
        assertThat(underTest.checkIfEmailLoggedIn(email)).isTrue();

        // clean up
        underTest.logout(underTest.getSessionID(email));
    }

    @Test
    void itShouldAddSessionSuccessfully2() {
        // given
        String email = "test@gmail.com";

        // when
        underTest.login(email, "student", 1L);

        // then
        assertThat(underTest.getUserIdFromSessionId(underTest.getSessionID(email))).isEqualTo(1L);

        // clean up
        underTest.logout(underTest.getSessionID(email));
    }

    @Test
    void itShouldLogoutSuccessfully() {
        // given
        String email = "test@gmail.com";

        // when
        underTest.login(email, "student", 1L);
        underTest.logout(underTest.getSessionID(email));

        // then
        assertThat(underTest.checkIfEmailLoggedIn(email)).isFalse();
    }

    @Test
    void itShouldChangeRoleSuccessfully() {
        // given
        String email = "test@gmail.com";

        // when
        underTest.login(email, "student", 1L);
        underTest.changeRole(email, "admin");
        String role = underTest.checkLogin(underTest.getSessionID(email))[1];
        // then
        assertThat(role).isEqualTo("admin");

        // clean up
        underTest.logout(underTest.getSessionID(email));
    }


}
