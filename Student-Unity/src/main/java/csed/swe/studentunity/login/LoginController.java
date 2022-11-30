package csed.swe.studentunity.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logIn")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestParam String email, @RequestParam String password) {
        String successfulLogin = loginService.checkCredentials(email, password);
        return new ResponseEntity<>(successfulLogin, HttpStatus.OK);
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email) {
        String doesEmailExists = loginService.forgetPassword(email);
        return new ResponseEntity<>(doesEmailExists, HttpStatus.OK);
    }

}
