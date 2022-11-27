package csed.swe.studentunity.account;

import csed.swe.studentunity.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
