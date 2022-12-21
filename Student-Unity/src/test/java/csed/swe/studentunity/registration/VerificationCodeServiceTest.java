package csed.swe.studentunity.registration;

import csed.swe.studentunity.logic.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class VerificationCodeServiceTest {

    @Autowired
    private VerificationCodeService underTest;

    @Test
    void itShouldAddVerificationCodeSuccessfully() {

        // given
        String email = "mam2542001@gmail.com";
        // when
        underTest.addVerificationCode(email);

        // then
        assertThat(underTest.getVerificationCode(email)).isNotNull();

        // clean up
        underTest.deleteVerificationCode(email);

    }

    @Test
    void itShouldDeleteVerificationCodeSuccessfuly() {
        // given
        String email = "mam2542001@gmail.com";
        // when
        underTest.addVerificationCode(email);

        // then
        underTest.deleteVerificationCode(email);
        assertThat(underTest.getVerificationCode(email)).isEmpty();

    }



}
