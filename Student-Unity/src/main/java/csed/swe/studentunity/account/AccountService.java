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

    List<User> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    List<User> searchAccounts(String searchString) {
            return accountRepo.searchAccounts(searchString);

    }

    Boolean changeRole(Integer targetUserId, String role) {
        accountRepo.changeRole(targetUserId, role);
        return true;
    }

    Boolean deleteAccount(Integer targetUserId) {
        accountRepo.deleteAccountByStudentId(targetUserId);
        return true;

    }

}
