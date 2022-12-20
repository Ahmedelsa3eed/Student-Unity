package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.RegistrationService;
import csed.swe.studentunity.model.RegistrationRequest;
import csed.swe.studentunity.model.RegistrationResponses;
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
    public ResponseEntity<RegistrationResponses> addUser(@RequestBody RegistrationRequest request) {
        RegistrationResponses registrationResponses = registrationService.addUser(request);
         return new ResponseEntity<>(registrationResponses, httpStatus(registrationResponses));
    }

    @PostMapping(path = "verify")
    public ResponseEntity<RegistrationResponses> verifyUser(@RequestBody VerificationRequest request) {
        RegistrationResponses registrationResponses = registrationService.verifyUser(request);
        return new ResponseEntity<>(registrationResponses, httpStatus(registrationResponses));
    }

    private HttpStatus httpStatus(RegistrationResponses registrationResponses) {
        if (registrationResponses == RegistrationResponses.SUCCESSFUL_REGISTRATION) {
            return HttpStatus.CREATED;
        } else if (registrationResponses == RegistrationResponses.SUCCESSFUL_VERIFICATION) {
            return HttpStatus.ACCEPTED;
        } else if (registrationResponses == RegistrationResponses.USER_ALREADY_EXISTS) {
            return HttpStatus.FORBIDDEN;
        } else if (registrationResponses == RegistrationResponses.USER_IS_NOT_REGISTERED) {
            return HttpStatus.NOT_FOUND;
        } else if (registrationResponses == RegistrationResponses.FAILED_VERIFICATION) {
            return HttpStatus.EXPECTATION_FAILED;
        } else if (registrationResponses == RegistrationResponses.USER_NEEDS_VERIFICATION) {
            return HttpStatus.FORBIDDEN;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }


}
