package csed.swe.studentunity.registration;

import csed.swe.studentunity.logic.UnverifiedUserService;
import csed.swe.studentunity.model.UnverifiedUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class UnverifiedUserServiceTest {
    @Autowired
    private UnverifiedUserService underTest;

    private final UnverifiedUser user = new UnverifiedUser("ahmed", 19016250, "ahmed", "elsaid", "12345", "student");

    @Test
    void itShouldAddUserSuccessfully() {
        // when
        underTest.addUnverifiedUser(user);

        // then
        Optional<UnverifiedUser> expected = underTest.getUnverifiedUser(user.getEmail());
        assertThat(user.getEmail()).isEqualTo(expected.get().getEmail());

        // clean up
        underTest.deleteUnverifiedUser(underTest.getUnverifiedUser(user.getEmail()).orElseThrow().getId());
    }

    @Test
    void itShouldDeleteUserSuccessfully() {
        // given
        underTest.addUnverifiedUser(user);

        // when
        underTest.deleteUnverifiedUser(underTest.getUnverifiedUser(user.getEmail()).orElseThrow().getId());

        // then
        Optional<UnverifiedUser> expected = underTest.getUnverifiedUser(user.getEmail());
        assertThat(expected).isEmpty();

    }




}
