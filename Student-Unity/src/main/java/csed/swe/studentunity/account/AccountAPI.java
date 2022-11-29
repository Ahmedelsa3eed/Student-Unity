package csed.swe.studentunity.account;

import csed.swe.studentunity.model.Account;
import csed.swe.studentunity.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountAPI {
    private final AccountService accountService;

    public AccountAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllAccounts(@Param("sessionId")String sessionId) {
        List<User> response = accountService.getAllAccounts(sessionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchAccounts(@Param("sessionId")String sessionId,
                                                       @Param("searchString")String searchString) {
        List<User> response = accountService.searchAccounts(sessionId, searchString);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changeRole")
    @ResponseBody
    public ResponseEntity<Boolean> changeRole(@RequestParam("sessionId")String sessionId,
                                              @RequestParam("targetUserId")Long targetUserId,
                                              @RequestParam("role")String role) {
        System.out.println("changeRole");
        System.out.println(sessionId);
        System.out.println(targetUserId);
        System.out.println(role);

        return new ResponseEntity<>(accountService.changeRole(sessionId, targetUserId, role), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@Param("sessionId")String sessionId,
                                                 @Param("targetUserId")Long targetUserId) {
        return new ResponseEntity<>(accountService.deleteAccount(sessionId, targetUserId), HttpStatus.OK);
    }

}
