package csed.swe.studentunity.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

@RequestMapping(path = "registration")
public class RegistrationController {
    private RegistrationLogic registrationLogic;


    @RequestMapping(path = "register")
    public String addUser(@RequestBody RegistrationRequest request) {
        return registrationLogic.addUser(request);
    }

    public String verifyUser() {
        return "works";
    }

}
