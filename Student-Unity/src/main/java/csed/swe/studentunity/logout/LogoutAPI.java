package csed.swe.studentunity.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/logout")
public class LogoutAPI {

        @Autowired
        private final LogoutService logoutService;

        public LogoutAPI(LogoutService logoutService) {
            this.logoutService = logoutService;
        }

        @PutMapping("/logout")
        public ResponseEntity<Boolean> logout(@RequestParam("sessionID") String sessionID) {
            return  new ResponseEntity<>(logoutService.logout(UUID.fromString(sessionID)), HttpStatus.OK);
        }
}
