package csed.swe.studentunity.login;

import csed.swe.studentunity.logic.LoginService;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.LoginResponses;
import csed.swe.studentunity.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LoginServiceTest {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;


    @Test
    public void correctLogin() {
        User user = new User("aka@gmail.com", 145, "amr", "essam", "12345", "Admin");
        userService.addUser(user);
        LoginResponses tesRes = loginService.checkCredentials("aka@gmail.com", "12345");
        assert LoginResponses.SUCCESSFUL_LOGIN == tesRes;
        userService.deleteUser(user.getId());


    }

    @Test
    public void wrongEmail () {
        LoginResponses tesRes = loginService.checkCredentials("ak@gmail.com", "12345");
        assert tesRes == LoginResponses.EMAIL_NOT_FOUND;
    }

    @Test
    public void wrongPassword(){
        User user = new User("aka@gmail.com", 1345, "amr", "essam", "12345", "Admin");
        userService.addUser(user);
        LoginResponses tesRes = loginService.checkCredentials("aka@gmail.com", "111");
        assert tesRes == LoginResponses.WRONG_PASSWORD;
        userService.deleteUser(user.getId());
    }

    @Test
    public void wrongEmailWrongPassword(){
        LoginResponses tesRes = loginService.checkCredentials("a@gmail.com", "154");
        assert tesRes == LoginResponses.EMAIL_NOT_FOUND;
    }
    @Test
    public void forgetPasswordWrongEmail(){
        boolean tesRes = loginService.forgetPassword("a@gmail.com");
        assert !tesRes;
    }
    @Test
    public void forgetPasswordCorrectEmail(){
        User user = new User("aka7897987987546wq@gmail.com", 1235, "amr", "essam", "12345", "Admin");
        userService.addUser(user);
        boolean tesRes = loginService.forgetPassword("aka7897987987546wq@gmail.com");
        assert tesRes;
        userService.deleteUser(user.getId());
    }

}