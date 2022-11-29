package csed.swe.studentunity.controllers;

import csed.swe.studentunity.logic.EmailService;
import csed.swe.studentunity.logic.LoginLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logIn")
public class LoginController {

    private final LoginLogic loginLogic;



    public LoginController(LoginLogic loginLogic){
        this.loginLogic = loginLogic;
    }

    @GetMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestParam String email, @RequestParam String password) {
        String succesfulLogin = loginLogic.checkCredentials(email, password);
        return new ResponseEntity<>(succesfulLogin, HttpStatus.OK);
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email) {
        String doesEmailExists = loginLogic.forgetPassword(email);
        return new ResponseEntity<>(doesEmailExists, HttpStatus.OK);
    }




}
