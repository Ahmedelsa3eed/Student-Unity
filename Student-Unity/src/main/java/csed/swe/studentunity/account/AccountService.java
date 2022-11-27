package csed.swe.studentunity.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    List<Object> getAllAccounts(String sessionId) {
        // call activeUser service
        return accountRepo.getAllAccounts();
    }

    List<Object> searchAccounts(String sessionId, String search) {
        // call activeUser service
        return accountRepo.searchAccounts(search);
    }

    Boolean changeRole(String sessionId, String id, String role) {
        // call activeUser service
         accountRepo.changeRole(id, role);
         return true;   // return true if successful
    }
}
