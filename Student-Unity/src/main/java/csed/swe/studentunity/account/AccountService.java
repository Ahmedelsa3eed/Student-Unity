package csed.swe.studentunity.account;

import csed.swe.studentunity.user.User;
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

    // return true if role is changed successfully
    Boolean changeRole(String sessionId, Integer targetUserId, String role) {
        // call activeUser service
         accountRepo.changeRole(targetUserId, role);
         return true;
    }

    // return true if account is deleted successfully
    Boolean deleteAccount(String sessionId, Integer targetUserId) {
        // call activeUser service
        accountRepo.deleteAccountByStudentId(targetUserId);
        return true;
    }

}
