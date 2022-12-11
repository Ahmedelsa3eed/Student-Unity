package csed.swe.studentunity.registration;

import csed.swe.studentunity.Logic.RegistrationService;
import csed.swe.studentunity.Logic.UserService;
import csed.swe.studentunity.model.RegistrationRequest;
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
    private final RegistrationRequest registrationRequest = new RegistrationRequest(
            "Adel", "Elsaid", "registrationTest@alexu.edu.eg", "25420", 11111111
    );


    @Test
    void itShouldAddUser() {
        // when
        String response = underTest.addUser(registrationRequest);

        // then
        String expected = "User registered successfully";
        assertThat(response).isEqualTo(expected);
    }

    @Test
    void itShouldFindUserExisted() {
        // when
        String response = underTest.addUser(registrationRequest);

        // then
        String expected = "User is already exist";
        assertThat(response).isEqualTo(expected);

        userService.deleteUser(userService.getUser("registrationTest@alexu.edu.eg").orElseThrow().getId());
    }


}