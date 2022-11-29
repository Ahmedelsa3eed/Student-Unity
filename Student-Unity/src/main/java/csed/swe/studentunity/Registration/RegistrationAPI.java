package csed.swe.studentunity.Registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "registration")
public class RegistrationAPI {

    private RegistrationService registrationService;

    @RequestMapping(path = "register")
    public String addUser(@RequestBody RegistrationRequest request) {
         return registrationService.addUser(request);
    }

    public String verifyUser() {
        return "works";
    }

}
