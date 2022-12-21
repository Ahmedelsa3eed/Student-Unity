package csed.swe.studentunity.logic.accounts;

import csed.swe.studentunity.dao.AccountRepo;
import csed.swe.studentunity.logic.user.ActiveUserService;
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

    public List<User> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    public List<User> searchAccounts(String searchString) {
        return accountRepo.searchAccounts(searchString);
    }

    public Boolean changeRole(String targetUserEmail, String role) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.changeRole(targetUserEmail, role);
        accountRepo.changeRole(targetUserEmail, role);
        return true;
    }

    public Boolean deleteAccount(String targetUserEmail) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.deleteSession(targetUserEmail);
        accountRepo.deleteAccountByEmail(targetUserEmail);
        return true;
    }

}
