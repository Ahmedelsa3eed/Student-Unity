package csed.swe.studentunity.account;

import csed.swe.studentunity.user.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountAPI {

    private final AccountService accountService;

    public AccountAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllAccounts(@Param("sessionId") String sessionId) {
        System.out.println("Session ID: " + sessionId);
        List<User> response = accountService.getAllAccounts();
        if(response == null){
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchAccounts(@Param("sessionId")String sessionId,
                                                       @Param("searchString")String searchString) {

        List<User> response = accountService.searchAccounts(searchString);
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changeRole")
    @ResponseBody
    public ResponseEntity<Boolean> changeRole(@RequestParam("sessionId")String sessionId,
                                              @RequestParam("targetUserId")Integer targetUserId,
                                              @RequestParam("role")String role) {
        Boolean response = accountService.changeRole(targetUserId, role);
        if(!response){
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@Param("sessionId")String sessionId,
                                                 @Param("targetUserId")Integer targetUserId) {
        Boolean response = accountService.deleteAccount(targetUserId);
        if(!response){
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
