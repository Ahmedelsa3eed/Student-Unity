package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.ActiveUserService;
import csed.swe.studentunity.Logic.LoginResponses;
import csed.swe.studentunity.Logic.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logIn")
public class LoginAPI {

    private final LoginService loginService;

    public LoginAPI(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestParam String email, @RequestParam String password) {
       LoginResponses successfulLogin = loginService.checkCredentials(email, password);
        if (successfulLogin == LoginResponses.SUCCESSFUL_LOGIN) {
            String sessionid = ActiveUserService.getInstance().getSessionIdAsString(email);
            return new ResponseEntity<>(sessionid , HttpStatus.OK);
        } else if (successfulLogin == LoginResponses.WRONG_PASSWORD) {
            return new ResponseEntity<>("Wrong Password", HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>("Email Not Found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<Boolean> forgetPassword(@RequestParam String email) {
        boolean doesEmailExists = loginService.forgetPassword(email);
        if (doesEmailExists) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
