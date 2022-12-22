package csed.swe.studentunity.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

@SpringBootTest

public class UserServiceTest {

    @Autowired
    private UserService underTest;

    private final User user = new User("ahmed", 19016250, "ahmed", "elsaid", "12345", "student");

    @Test
    void itShouldAddUserSuccessfully() {
        // when
        underTest.addUser(user);

        // then
        Optional<User> expected = underTest.getUser(user.getEmail());
        assertThat(user.getEmail()).isEqualTo(expected.get().getEmail());

        // clean up
        underTest.deleteUser(underTest.getUser(user.getEmail()).orElseThrow().getId());
    }

    @Test
    void itShouldDeleteUserSuccessfully() {
        // given
        underTest.addUser(user);

        // when
        underTest.deleteUser(underTest.getUser(user.getEmail()).orElseThrow().getId());

        // then
        Optional<User> expected = underTest.getUser(user.getEmail());
        assertThat(expected).isEmpty();

    }



}
