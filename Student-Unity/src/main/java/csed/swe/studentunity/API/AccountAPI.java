package csed.swe.studentunity.API;

import csed.swe.studentunity.Logic.Accounts.AccountService;
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
    public ResponseEntity<List<User>> getAllAccounts() {
        List<User> response = accountService.getAllAccounts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchAccounts(@Param("searchString")String searchString) {
        List<User> response = accountService.searchAccounts(searchString);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changeRole")
    @ResponseBody
    public ResponseEntity<Boolean> changeRole(@RequestParam("targetUserId")Integer targetUserId,
                                              @RequestParam("role")String role) {
        return new ResponseEntity<>(accountService.changeRole(targetUserId, role), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@Param("targetUserId")Integer targetUserId) {
        return new ResponseEntity<>(accountService.deleteAccount(targetUserId), HttpStatus.OK);
    }

}
