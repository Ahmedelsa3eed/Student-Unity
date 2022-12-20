package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.RegistrationService;
import csed.swe.studentunity.model.RegistrationRequest;
import csed.swe.studentunity.model.VerificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "registration")
public class RegistrationAPI {

    private RegistrationService registrationService;

    @PostMapping(path = "register")
    public ResponseEntity<String> addUser(@RequestBody RegistrationRequest request) {
         return new ResponseEntity<>(registrationService.addUser(request), HttpStatus.CREATED);
    }

    @PostMapping(path = "verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerificationRequest request) {
        return new ResponseEntity<>(registrationService.verifyUser(request), HttpStatus.OK);
    }

}
