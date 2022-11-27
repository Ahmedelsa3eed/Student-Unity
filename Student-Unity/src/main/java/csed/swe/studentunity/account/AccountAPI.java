package csed.swe.studentunity.account;

import csed.swe.studentunity.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountAPI {
    private final AccountService accountService;

    public AccountAPI(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllAccounts(@Param("sessionId")String sessionId) {
        List<Object> response = accountService.getAllAccounts(sessionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object>> searchAccounts(@Param("sessionId")String sessionId, @Param("search")String search) {
        List<Object> response = accountService.searchAccounts(sessionId, search);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changeRole")
    public ResponseEntity<Boolean> changeRole(@Param("sessionId")String sessionId, @Param("id")String id, @Param("role")String role) {
        return new ResponseEntity<>(accountService.changeRole(sessionId, id, role), HttpStatus.OK);
    }

}
