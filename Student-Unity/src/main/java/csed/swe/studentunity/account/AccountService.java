package csed.swe.studentunity.account;

import csed.swe.studentunity.model.Account;
import csed.swe.studentunity.model.User;
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

    List<User> getAllAccounts(String sessionId) {
        // call activeUser service
        return accountRepo.getAllAccounts();
    }

    List<User> searchAccounts(String sessionId, String searchString) {
        // call activeUser service
        return accountRepo.searchAccounts(searchString);
    }

    Boolean changeRole(String sessionId, Long targetUserId, String role) {
        // call activeUser service
         accountRepo.changeRole(targetUserId, role);
         return true;   // return true if successful
    }

    Boolean deleteAccount(String sessionId, Long targetUserId) {
        // call activeUser service
        accountRepo.deleteAccountById(targetUserId);
        return true;   // return true if successful
    }

}
