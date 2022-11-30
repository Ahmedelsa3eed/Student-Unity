package csed.swe.studentunity.account;

import csed.swe.studentunity.sharedServices.ActiveUserService;
import csed.swe.studentunity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountService {

    private final AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    List<User> getAllAccounts(UUID sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        if (activeUserService.checkLogin(sessionId)[1].equals("admin")) {
            return accountRepo.getAllAccounts();
        }else{
            return null;
        }

    }

    List<User> searchAccounts(UUID sessionId, String searchString) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        if (activeUserService.checkLogin(sessionId)[1].equals("admin")) {
            return accountRepo.searchAccounts(searchString);
        }else{
            return null;
        }
    }

    Boolean changeRole(UUID sessionId, Integer targetUserId, String role) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        if (activeUserService.checkLogin(sessionId)[1].equals("admin")) {
            accountRepo.changeRole(targetUserId, role);
            return true;
        }else{
            return false;
        }

    }

    Boolean deleteAccount(UUID sessionId, Integer targetUserId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        if (activeUserService.checkLogin(sessionId)[1].equals("admin")) {
            accountRepo.deleteAccountByStudentId(targetUserId);
            return true;
        }else{
            return false;
        }
    }

}
